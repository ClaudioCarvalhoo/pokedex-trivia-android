package com.example.pokequizz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import kotlinx.android.synthetic.main.activity_main.*
import com.example.pokequizz.Activities.FindRoom
import com.example.pokequizz.ApiHelper.RetrofitInitializer

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createRoomBttn.setOnClickListener {
            println("Clicked on main button")
        }

        findRoomBttn.setOnClickListener {
            val intent = Intent(this, FindRoom::class.java)
            startActivity(intent)
        }
    }
}
