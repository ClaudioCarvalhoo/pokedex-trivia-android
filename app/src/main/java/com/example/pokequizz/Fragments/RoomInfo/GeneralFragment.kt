package com.example.pokequizz.Fragments.RoomInfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.pokequizz.ApiHelper.Entities.Room
import com.example.pokequizz.R
import com.example.pokequizz.ViewModels.RoomInfoViewModel
import kotlinx.android.synthetic.main.fragment_room_info_general.*

/**
 * A placeholder fragment containing a simple view.
 */
class GeneralFragment : Fragment() {

    private lateinit var roomInfoViewModel: RoomInfoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        roomInfoViewModel = ViewModelProviders.of(this).get(RoomInfoViewModel::class.java).apply {
            setQtdOfQuestions(arguments?.getInt(ARG_QTD_QUESTIONS) ?: 0)
            setRoomId(arguments?.getString(ARG_ROOM_ID) ?: "")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_room_info_general, container, false)

        roomInfoViewModel.qtdOfQuestions.observe(this, Observer<String> {
            number_of_questions.text = it
        })

        roomInfoViewModel.roomId.observe(this, Observer<String> {
            room_id.text = it
        })

        return root
    }

    companion object {

        private const val ARG_ROOM_ID = "room_id"
        private const val ARG_QTD_QUESTIONS = "qtd_questions"

        @JvmStatic
        fun newInstance(room: Room?): GeneralFragment {
            return GeneralFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_ROOM_ID, room?.id)
                    putInt(ARG_QTD_QUESTIONS, room?.questions?.size ?: 0)
                }
            }
        }
    }
}