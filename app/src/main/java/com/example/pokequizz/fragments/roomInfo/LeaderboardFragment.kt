package com.example.pokequizz.fragments.roomInfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pokequizz.R
import com.example.pokequizz.adapters.LeaderboardAdapter
import com.example.pokequizz.apiHelper.entities.Entry
import com.example.pokequizz.apiHelper.entities.Room
import java.io.Serializable

class LeaderboardFragment : Fragment() {

    private lateinit var leaderboard : List<Entry>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            leaderboard = it.getSerializable(ARG_LEADERBOARD) as List<Entry>
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_leaderboard_list, container, false)

        if (view is RecyclerView) {
            with(view) {
                layoutManager  = GridLayoutManager(context, 1)
                adapter = LeaderboardAdapter(leaderboard)
            }
        }
        return view
    }

    companion object {

        const val ARG_LEADERBOARD = "leaderboard"

        @JvmStatic
        fun newInstance(room : Room?) =
            LeaderboardFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_LEADERBOARD, room?.leaderboard as Serializable)
                }
            }
    }
}
