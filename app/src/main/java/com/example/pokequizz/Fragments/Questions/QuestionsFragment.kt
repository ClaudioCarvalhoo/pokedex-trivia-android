package com.example.pokequizz.Fragments.Questions

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pokequizz.ApiHelper.Entities.Question
import androidx.lifecycle.Observer
import com.example.pokequizz.R
import com.example.pokequizz.ViewModels.QuestionsViewModel
import kotlinx.android.synthetic.main.questions_fragment.*

class QuestionsFragment : Fragment() {

    private lateinit var questionsViewModel: QuestionsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.questions_fragment, container, false)

        questionsViewModel.stem.observe(this, Observer<String> {
            question_title.text = it
        })

        return root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        questionsViewModel = ViewModelProviders.of(this).get(QuestionsViewModel::class.java).apply {
            setStem(arguments?.getString(ARG_STEM) ?: "")
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        questionsViewModel = ViewModelProviders.of(this).get(QuestionsViewModel::class.java)
    }

    companion object {
        private const val ARG_STEM = "stem"

        @JvmStatic
        fun newInstance(question: Question) : QuestionsFragment {
            return QuestionsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_STEM, question?.stem)
                }
            }
        }
    }
}
