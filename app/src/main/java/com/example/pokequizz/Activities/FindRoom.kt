package com.example.pokequizz.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.pokequizz.Adapters.FindRoomAdapter
import com.example.pokequizz.R
import com.example.pokequizz.ApiHelper.ApiHelper.getRooms
import com.example.pokequizz.ApiHelper.Entities.Summary

class FindRoom : AppCompatActivity() {

    private var rooms : List<Summary> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.rooms = getRooms()

        setContentView(R.layout.activity_find_room)
        setSupportActionBar(findViewById(R.id.find_room_toolbar))
        setRecyclerView()
    }

    private fun setRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.find_room_rv)

        /* Set recycler view size as fixed for performance */
        recyclerView.setHasFixedSize(true)

        recyclerView.adapter = FindRoomAdapter(rooms, this, onItemClickListener)
        val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
    }

    private val onItemClickListener = View.OnClickListener { view ->
        val viewHolder = view.tag as RecyclerView.ViewHolder
        val room = this.rooms[viewHolder.adapterPosition]

        val intent = Intent(this, RoomInfo::class.java)
        val b = Bundle()
        b.putString("room_id", room.id)
        intent.putExtras(b)
        startActivity(intent)
    }
}
