package com.example.pokequizz.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokequizz.ApiHelper.Entities.Summary
import com.example.pokequizz.R
import kotlinx.android.synthetic.main.find_room_item.view.*

class FindRoomAdapter(
    private val rooms: List<Summary>,
    private val context: Context,
    private val onItemClickListener: View.OnClickListener)
    : RecyclerView.Adapter<FindRoomAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val room = rooms[position]
        holder.bindView(room, onItemClickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.find_room_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return rooms.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(room: Summary, clickListener: View.OnClickListener) {
            val roomId = itemView.room_id
            val categories = itemView.find_room_categories

            roomId.text = "Room #${room.id}"
            categories.text = room.categories.map {
                it.name
            }.joinToString(separator = " • ")

            itemView.setTag(this)
            itemView.setOnClickListener(clickListener)
        }
    }
}