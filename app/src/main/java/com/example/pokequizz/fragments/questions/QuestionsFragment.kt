package com.example.pokequizz.fragments.questions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.pokequizz.apiHelper.entities.Question
import com.example.pokequizz.R
import com.example.pokequizz.activities.Questions
import com.example.pokequizz.apiHelper.entities.Alternative
import com.example.pokequizz.apiHelper.entities.Answer
import com.example.pokequizz.viewModels.QuestionsViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.questions_fragment.imageView
import kotlinx.android.synthetic.main.questions_fragment.question_title
import kotlinx.android.synthetic.main.questions_fragment.view.alternatives_radio_group
import java.io.Serializable

class QuestionsFragment : Fragment() {

    private lateinit var questionsViewModel: QuestionsViewModel
    private var root : View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        root = inflater.inflate(R.layout.questions_fragment, container, false)

        questionsViewModel.id.observe(this, Observer<String> {
            root!!.alternatives_radio_group.tag = it
        })

        questionsViewModel.stem.observe(this, Observer<String> {
            question_title.text = it
        })

        questionsViewModel.imageUrl.observe(this, Observer<String> {
            if (!it.isEmpty() && imageView.getDrawable() == null) {
                Picasso.get().load(it).into(imageView)
            } else {
                imageView.visibility = View.GONE
            }
        })

        questionsViewModel.pair.observe(this, Observer<Pair<List<Alternative>, ArrayList<Answer>>> {
            val alternatives = it.first
            val answers = it.second

            for ((i, alternative) in alternatives.withIndex()) {
                val radioButton = RadioButton(getActivity())
                radioButton?.layoutParams= LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT)
                radioButton?.setText(alternative.text)
                radioButton.id = i

                if (answers.find { it.choice == alternative.id } != null) {
                    radioButton.isChecked = true
                }

                root!!.alternatives_radio_group!!.addView(radioButton)
            }

            root!!.alternatives_radio_group.setOnCheckedChangeListener { group, checkedId ->
                Questions.onAlternativeSelection(group.tag.toString(), checkedId)
            }
        })


        return root!!
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true

        if (savedInstanceState == null) {
            questionsViewModel = ViewModelProviders.of(this).get(QuestionsViewModel::class.java).apply {
                setId(arguments?.getString(ARG_ID) ?: "")
                setStem(arguments?.getString(ARG_STEM) ?: "")
                setImageUrl(arguments?.getString(ARG_IMAGE_URL) ?: "")
                setPair(
                    arguments?.getSerializable(ARG_ALTERNATIVES) as List<Alternative>,
                    arguments?.getSerializable(ARG_ANSWERS) as ArrayList<Answer>
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        /* Removing risk of leaks */
        root = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        questionsViewModel = ViewModelProviders.of(this).get(QuestionsViewModel::class.java)
    }

    companion object {
        private const val ARG_ID = "id"
        private const val ARG_STEM = "stem"
        private const val ARG_IMAGE_URL = "image_url"
        private const val ARG_ALTERNATIVES = "alternatives"
        private const val ARG_ANSWERS = "answers"
        private lateinit var question : Question

        @JvmStatic
        fun newInstance(question: Question, answers: ArrayList<Answer>): QuestionsFragment {
            this.question = question

            return QuestionsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_ID, question.id)
                    putString(ARG_STEM, question.stem)
                    putString(ARG_IMAGE_URL, question.imageUrl)
                    putSerializable(ARG_ALTERNATIVES, question.alternatives as Serializable)
                    putSerializable(ARG_ANSWERS, answers as Serializable)
                }
            }
        }
    }
}
