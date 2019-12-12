package com.example.pokequizz.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.pokequizz.Adapters.FindRoomAdapter
import com.example.pokequizz.R
import com.example.pokequizz.ApiHelper.ApiHelper.getRooms
import com.example.pokequizz.ApiHelper.Entities.Summary

class FindRoom : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_room)

        setSupportActionBar(findViewById(R.id.find_room_toolbar))

        val rv = findViewById<RecyclerView>(R.id.find_room_rv)

        /* Fix recylcler view size for performance */
        rv.setHasFixedSize(true)

        rv.adapter = FindRoomAdapter(rooms(), this)
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        rv.layoutManager = layoutManager
    }

    private fun rooms(): List<Summary> {
        return getRooms()
    }
}
