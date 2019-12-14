package com.example.pokequizz.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.viewpager.widget.ViewPager
import com.example.pokequizz.Adapters.QuestionsAdapter
import com.example.pokequizz.ApiHelper.Entities.Question
import com.example.pokequizz.R
import kotlinx.android.synthetic.main.questions_activity.*

class Questions : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.questions_activity)

        val bundle = intent.extras
        val questions = bundle?.getSerializable("questions") as List<Question>

        setViewPager(questions)
    }

    private fun setViewPager(questions: List<Question>) {
        val questionsMap = questions.map { "" }
        step_view.setSteps(questionsMap)

        val questionsAdapter =
            QuestionsAdapter(this, supportFragmentManager, questions)

        view_pager_questions.adapter = questionsAdapter

        view_pager_questions.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                step_view.go(position, false);
            }

            override fun onPageSelected(position: Int) {}
        })
    }
}
