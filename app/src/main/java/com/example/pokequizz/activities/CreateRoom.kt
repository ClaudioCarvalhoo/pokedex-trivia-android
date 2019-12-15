package com.example.pokequizz.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.LinearLayout
import com.example.pokequizz.R
import com.example.pokequizz.apiHelper.RetrofitFacade
import com.example.pokequizz.apiHelper.entities.Category
import com.example.pokequizz.apiHelper.entities.CreateRoomRequest
import kotlinx.android.synthetic.main.activity_create_room.create_bttn
import kotlinx.android.synthetic.main.activity_create_room.create_room_checkboxes
import kotlinx.android.synthetic.main.activity_create_room.create_room_content
import kotlinx.android.synthetic.main.activity_create_room.create_room_loading
import kotlinx.android.synthetic.main.activity_create_room.create_room_seek_bar
import org.jetbrains.anko.longToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateRoom : AppCompatActivity() {

    private var selectedCategories : ArrayList<String> = arrayListOf()
    private lateinit var subcategories : List<Category>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_room)

        val call = RetrofitFacade.retrofit.getCategories()
        call.enqueue(object : Callback<List<Category>?> {
            override fun onResponse(
                call: Call<List<Category>?>?,
                response: Response<List<Category>?>?
            ) {
                response?.body()?.let {
                    subcategories = it.flatMap { it.subcategories }
                    setCheckboxes()
                    setCreateBttn()
                    create_room_loading.visibility = View.GONE
                    create_room_content.visibility = View.VISIBLE
                }
            }

            override fun onFailure(
                call: Call<List<Category>?>?,
                t: Throwable
            ) {
                longToast(t.message.toString())
            }
        })
    }

    private fun setCheckboxes() {
        for((index, category) in subcategories.withIndex()) {
            val checkbox = CheckBox(this)
            checkbox.layoutParams= LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)

            checkbox.text = category.name
            checkbox.id = index

            checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
                val selectedCategory = subcategories[buttonView.id]

                if (isChecked) {
                    selectedCategories.add(selectedCategory.id)
                } else {
                    selectedCategories.remove(selectedCategory.id)
                }
            }

            create_room_checkboxes.addView(checkbox)
        }
    }

    private fun setCreateBttn() {
        create_bttn.setOnClickListener {

            val createRoomRequest = CreateRoomRequest(
                create_room_seek_bar.progress,
                selectedCategories
            )

            create_room_loading.visibility = View.VISIBLE
            create_room_content.visibility = View.GONE

            val call = RetrofitFacade.retrofit.createRoom(createRoomRequest)
            call.enqueue(object : Callback<String?> {
                override fun onResponse(
                    call: Call<String?>?,
                    response: Response<String?>?
                ) {
                    response?.body()?.let {
                        goToRoomInfo(it)
                    }
                }

                override fun onFailure(
                    call: Call<String?>?,
                    t: Throwable
                ) {
                    longToast(t.message.toString())
                }
            })
        }
    }

    private fun goToRoomInfo(roomId : String) {
        val intent = Intent(this, RoomInfo::class.java)
        val b = Bundle()
        b.putString("roomId", roomId)
        b.putString("message", "Room created successfully!")
        intent.putExtras(b)
        startActivity(intent)
    }
}
