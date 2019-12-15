package com.example.pokequizz.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.pokequizz.adapters.FindRoomAdapter
import com.example.pokequizz.apiHelper.entities.Summary
import com.example.pokequizz.apiHelper.RetrofitFacade
import com.example.pokequizz.R
import kotlinx.android.synthetic.main.activity_find_room.*
import org.jetbrains.anko.longToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FindRoom : AppCompatActivity() {

    private var rooms: List<Summary> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_room)
        setSupportActionBar(find_room_toolbar)
        setFab()

        val call = RetrofitFacade.retrofit.getRooms()
        call.enqueue(object : Callback<List<Summary>?> {
            override fun onResponse(
                call: Call<List<Summary>?>?,
                response: Response<List<Summary>?>?
            ) {
                response?.body()?.let {
                    rooms = it.sortedBy { -it.id.toInt() }
                }

                setRecyclerView(rooms)
                find_room_loading.visibility = View.GONE
            }

            override fun onFailure(
                call: Call<List<Summary>?>?,
                t: Throwable
            ) {
                longToast(t.message.toString())
            }
        })
    }

    private fun setRecyclerView(rooms: List<Summary>) {
        /* Set fixed recycler size for performance */
        find_room_rv.setHasFixedSize(true)

        find_room_rv.adapter = FindRoomAdapter(rooms, this, onItemClickListener)
        val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        find_room_rv.layoutManager = layoutManager
    }

    private fun setFab() {
        add_room_fab.setOnClickListener { toCreateActivity() }
    }

    private val onItemClickListener = View.OnClickListener { view ->
        val viewHolder = view.tag as RecyclerView.ViewHolder
        val room = this.rooms[viewHolder.adapterPosition]

        val intent = Intent(this, RoomInfo::class.java)
        val b = Bundle()
        b.putString("roomId", room.id)
        intent.putExtras(b)
        startActivity(intent)
    }

    private fun toCreateActivity() {
        val intent = Intent(this, CreateRoom::class.java)
        startActivity(intent)
    }
}
