package com.example.pokequizz.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.pokequizz.Adapters.FindRoomAdapter
import com.example.pokequizz.R
import com.example.pokequizz.ApiHelper.Entities.Summary
import com.example.pokequizz.ApiHelper.RetrofitFacade
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_find_room.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FindRoom : AppCompatActivity() {

    private var rooms : List<Summary> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_room)
        setSupportActionBar(find_room_toolbar)

        val call = RetrofitFacade.retrofit.getRooms()
        call.enqueue(object: Callback<List<Summary>?> {
            override fun onResponse(call: Call<List<Summary>?>?,
                                    response: Response<List<Summary>?>?) {
                response?.body()?.let {
                    rooms = it
                }

                find_room_loading.visibility = View.INVISIBLE
                setRecyclerView(rooms)
            }

            override fun onFailure(call: Call<List<Summary>?>?,
                                   t: Throwable) {
                Log.e("onFailure error", t.message.toString())
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
