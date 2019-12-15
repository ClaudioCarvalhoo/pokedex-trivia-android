package com.example.pokequizz.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pokequizz.R
import com.example.pokequizz.apiHelper.entities.Entry
import kotlinx.android.synthetic.main.fragment_leaderboard.view.leaderboard_score
import kotlinx.android.synthetic.main.fragment_leaderboard.view.leaderboard_username

class LeaderboardAdapter(
    private val leaderboard: List<Entry>
) : RecyclerView.Adapter<LeaderboardAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_leaderboard, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val entry = leaderboard[position]
        holder.bindView(entry)
    }

    override fun getItemCount(): Int = leaderboard.size

    inner class ViewHolder(private val mView: View) : RecyclerView.ViewHolder(mView) {
        fun bindView(entry: Entry) {
            mView.leaderboard_username.text = entry.username
            mView.leaderboard_score.text = entry.score.toString()
        }
    }
}
