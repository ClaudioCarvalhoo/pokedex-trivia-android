package com.example.pokequizz.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.example.pokequizz.adapters.RoomInfoAdapter
import com.example.pokequizz.apiHelper.entities.Room
import com.example.pokequizz.apiHelper.RetrofitFacade
import com.example.pokequizz.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import java.io.Serializable
import kotlinx.android.synthetic.main.activity_room_info.*
import org.jetbrains.anko.longToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RoomInfo : AppCompatActivity() {

    private var room: Room? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_info)

        setSupportActionBar(room_info_toolbar as Toolbar)

        val b = intent.extras
        var roomId = b?.getString("roomId").toString()
        var message = b?.getString("message")

        if (message != null) {
            longToast(message.toString())
        }

        val call = RetrofitFacade.retrofit.getRoom(roomId)
        call.enqueue(object : Callback<Room?> {
            override fun onResponse(
                call: Call<Room?>?,
                response: Response<Room?>?
            ) {
                response?.body()?.let {
                    room = it
                }

                room_info_loading.visibility = View.INVISIBLE
                setRoomInfoView(room)
            }

            override fun onFailure(
                call: Call<Room?>?,
                t: Throwable?
            ) {
                longToast(t?.message ?: "Unknown error")
            }
        })
    }

    private fun setRoomInfoView(room: Room?) {
        val roomInfoAdapter =
            RoomInfoAdapter(this, supportFragmentManager, room)
        val viewPager: ViewPager = view_pager
        viewPager.adapter = roomInfoAdapter
        val tabs: TabLayout = tabs
        tabs.setupWithViewPager(viewPager)
        val fab: FloatingActionButton = fab

        fab.setOnClickListener {
            changeActivity()
        }

        if (supportActionBar != null) {
            Log.e("EAE", "EAE")
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun changeActivity() {
        val intent = Intent(this, Questions::class.java)
        val bundle = Bundle()
        bundle.putSerializable("questions", room?.questions as Serializable)
        bundle.putString("roomId", room?.id)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    override fun onSupportNavigateUp(): Boolean {
        val intent = Intent(this, FindRoom::class.java)
        val bundle = Bundle()
        intent.putExtras(bundle)
        startActivity(intent)

        return true
    }
}
