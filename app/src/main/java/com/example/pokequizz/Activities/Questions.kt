package com.example.pokequizz.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.viewpager.widget.ViewPager
import com.example.pokequizz.Adapters.QuestionsAdapter
import com.example.pokequizz.ApiHelper.Entities.Question
import com.example.pokequizz.Fragments.Questions.QuestionsFragment
import com.example.pokequizz.R

class Questions : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.questions_activity)

        val bundle = intent.extras
        val questions = bundle?.getSerializable("questions") as List<Question>

//        if (savedInstanceState == null) {
//            val questionsAdapter =
//                QuestionsAdapter(this, supportFragmentManager, questions)
//            val viewPager: ViewPager = view_pager
//            viewPager.adapter = roomInfoAdapter
//            val tabs: TabLayout = tabs
//            tabs.setupWithViewPager(viewPager)
//        }
//
//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.container, QuestionsFragment.newInstance())
//                .commitNow()
//        }
    }

}
