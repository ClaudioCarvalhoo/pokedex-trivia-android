package com.example.pokequizz.Activities

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

    private val onItemClickListener = View.OnClickListener { view ->
        val viewHolder = view.tag as RecyclerView.ViewHolder
        val position = viewHolder.adapterPosition
        println(position)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_room)

        setSupportActionBar(findViewById(R.id.find_room_toolbar))
        setRecyclerView()
    }

    private fun setRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.find_room_rv)

        /* Set recycler view size as fixed for performance */
        recyclerView.setHasFixedSize(true)

        recyclerView.adapter = FindRoomAdapter(rooms(), this, onItemClickListener)
        val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
    }

    private fun rooms(): List<Summary> {
        return getRooms()
    }
}
