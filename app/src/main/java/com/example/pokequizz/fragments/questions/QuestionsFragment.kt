package com.example.pokequizz.fragments.questions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.pokequizz.apiHelper.entities.Question
import com.example.pokequizz.R
import com.example.pokequizz.activities.Questions
import com.example.pokequizz.adapters.AlternativesAdapter
import com.example.pokequizz.apiHelper.entities.Alternative
import com.example.pokequizz.viewModels.QuestionsViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.questions_fragment.alternatives_list_view
import kotlinx.android.synthetic.main.questions_fragment.imageView
import kotlinx.android.synthetic.main.questions_fragment.question_title
import java.io.Serializable

class QuestionsFragment : Fragment() {

    private lateinit var questionsViewModel: QuestionsViewModel
    private lateinit var listView : ListView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.questions_fragment, container, false)

        questionsViewModel.stem.observe(this, Observer<String> {
            question_title.text = it
        })

        questionsViewModel.imageUrl.observe(this, Observer<String> {
            if (!it.isEmpty()) {
                Picasso.get().load(it).into(imageView)
            } else {
                imageView.visibility = View.GONE
            }
        })

        questionsViewModel.alternatives.observe(this, Observer<List<Alternative>> {
            listView = alternatives_list_view
            val adapter = AlternativesAdapter(activity as Questions, it)
            listView.adapter = adapter
        })

        return root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        questionsViewModel = ViewModelProviders.of(this).get(QuestionsViewModel::class.java).apply {
            setStem(arguments?.getString(ARG_STEM) ?: "")
            setImageUrl(arguments?.getString(ARG_IMAGE_URL) ?: "")
            setAlternatives(arguments?.getSerializable(ARG_ALTERNATIVES) as List<Alternative>)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        questionsViewModel = ViewModelProviders.of(this).get(QuestionsViewModel::class.java)
    }

    companion object {
        private const val ARG_STEM = "stem"
        private const val ARG_IMAGE_URL = "image_url"
        private const val ARG_ALTERNATIVES = "alternatives"
        private var question : Question? = null

        @JvmStatic
        fun newInstance(question: Question): QuestionsFragment {
            this.question = question

            return QuestionsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_STEM, question?.stem)
                    putString(ARG_IMAGE_URL, question?.imageUrl)
                    putSerializable(ARG_ALTERNATIVES, question!!.alternatives as Serializable)
                }
            }
        }
    }
}