package com.example.pokequizz.Activities

import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import com.example.pokequizz.Adapters.SectionsPagerAdapter
import com.example.pokequizz.ApiHelper.Entities.Room
import com.example.pokequizz.R
import com.example.pokequizz.ApiHelper.RetrofitFacade
import kotlinx.android.synthetic.main.activity_room_info.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
                Log.e("onFailure error", t?.message)
            }
        })
    }

    private fun setRoomInfoView(room: Room?) {
        val sectionsPagerAdapter =
            SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = view_pager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = tabs
        tabs.setupWithViewPager(viewPager)
        val fab: FloatingActionButton = fab

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }
}