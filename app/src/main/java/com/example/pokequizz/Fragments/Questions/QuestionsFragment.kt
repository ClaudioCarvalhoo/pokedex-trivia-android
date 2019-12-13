package com.example.pokequizz.Fragments.Questions

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pokequizz.ApiHelper.Entities.Question
import com.example.pokequizz.R
import com.example.pokequizz.ViewModels.QuestionsViewModel

class QuestionsFragment : Fragment() {

    companion object {
        fun newInstance(question: Question) = QuestionsFragment()
    }

    private lateinit var viewModel: QuestionsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.questions_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(QuestionsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
