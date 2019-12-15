package com.example.pokequizz.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.pokequizz.adapters.QuestionsAdapter
import com.example.pokequizz.apiHelper.entities.Question
import com.example.pokequizz.R
import com.example.pokequizz.apiHelper.entities.Answer
import kotlinx.android.synthetic.main.questions_activity.*

class Questions : AppCompatActivity() {

    private val answers : ArrayList<Answer> = arrayListOf()
    private lateinit var questions : List<Question>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.questions_activity)

        if (savedInstanceState == null) {
            val bundle = intent.extras
            questions = bundle?.getSerializable("questions") as List<Question>

            setViewPager(questions)
        }
    }

    private fun setViewPager(questions: List<Question>) {
        val questionsMap = questions.map { "" }
        step_view.setSteps(questionsMap)

        val questionsAdapter =
            QuestionsAdapter(this, supportFragmentManager, questions, this::onAlternativeSelection)

        view_pager_questions.adapter = questionsAdapter

        view_pager_questions.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                step_view.go(position, false)
            }

            override fun onPageSelected(position: Int) {}
        })
    }

    private fun onAlternativeSelection(questionId: String, alternativeIndex: Int) {
        var question = questions.find { it.id == questionId }
        var alternative = question!!.alternatives!![alternativeIndex]
        var answer = answers.find { it.questionId == question.id }

        if (answer == null) {
            answers.add(Answer(questionId, alternative.id))
        } else {
            answer.choice = alternative.id
        }

        if (answers.size == questions.size) {
            submit_bttn.isEnabled = true
        }
    }
}
