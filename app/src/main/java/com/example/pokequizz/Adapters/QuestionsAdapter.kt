package com.example.pokequizz.Adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.pokequizz.ApiHelper.Entities.Question
import com.example.pokequizz.Fragments.Questions.QuestionsFragment

class QuestionsAdapter(private val context: Context, fm: FragmentManager, questions: List<Question>) :
    FragmentPagerAdapter(fm) {

    private val questions = questions

    override fun getItem(position: Int): Fragment {
        val question = questions[position]
        return QuestionsFragment.newInstance(question)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return "Q${position}"
    }

    override fun getCount(): Int {
        return questions.size
    }
}