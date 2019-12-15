package com.example.pokequizz.adapters

import android.content.Context
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.pokequizz.apiHelper.entities.Answer
import com.example.pokequizz.apiHelper.entities.Question
import com.example.pokequizz.fragments.questions.QuestionsFragment

class QuestionsAdapter(
    private val context: Context,
    fm: FragmentManager,
    questions: List<Question>,
    private val answers: ArrayList<Answer>
    ) : FragmentPagerAdapter(fm) {

    private val questions = questions

    override fun getItem(position: Int): Fragment {
        val question = questions[position]
        return QuestionsFragment.newInstance(question, answers)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return "Q$position"
    }

    override fun getCount(): Int {
        return questions.size
    }
}
