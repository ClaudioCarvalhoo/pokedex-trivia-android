package com.example.pokequizz.Activities

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import com.example.pokequizz.Activities.ui.main.SectionsPagerAdapter
import com.example.pokequizz.ApiHelper.ApiHelper
import com.example.pokequizz.ApiHelper.Entities.Room
import com.example.pokequizz.R

class RoomInfo : AppCompatActivity() {

    private var room : Room? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setRoom()

        setContentView(R.layout.activity_room_info)
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
        val fab: FloatingActionButton = findViewById(R.id.fab)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    private fun setRoom() {
        val b = intent.extras
        var roomId = b?.getString("room_id").toString()
        var room = ApiHelper.getRoom(roomId)

        this.room = room
        println(this.room?.id)
    }
}