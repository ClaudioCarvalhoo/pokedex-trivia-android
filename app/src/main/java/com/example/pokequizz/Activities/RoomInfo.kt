package com.example.pokequizz.Activities

import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import com.example.pokequizz.Adapters.RoomInfoAdapter
import com.example.pokequizz.ApiHelper.Entities.Room
import com.example.pokequizz.R
import com.example.pokequizz.ApiHelper.RetrofitFacade
import kotlinx.android.synthetic.main.activity_room_info.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.content.Intent
import org.jetbrains.anko.longToast
import java.io.Serializable

class RoomInfo : AppCompatActivity() {

    private var room : Room? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_info)

        val b = intent.extras
        var roomId = b?.getString("room_id").toString()

        val call = RetrofitFacade.retrofit.getRoom(roomId)
        call.enqueue(object: Callback<Room?> {
            override fun onResponse(call: Call<Room?>?,
                                    response: Response<Room?>?) {
                response?.body()?.let {
                    room = it
                }

                room_info_loading.visibility = View.INVISIBLE
                setRoomInfoView(room)
            }

            override fun onFailure(call: Call<Room?>?,
                                   t: Throwable?) {
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
    }

    private fun changeActivity() {
        val intent = Intent(this, Questions::class.java)
        val bundle = Bundle()
        bundle.putSerializable("questions", room?.questions as Serializable)
        intent.putExtras(bundle)
        startActivity(intent)
    }
}