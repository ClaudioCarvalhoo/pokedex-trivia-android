package com.example.pokequizz

import android.app.Application
import com.frogermcs.androiddevmetrics.AndroidDevMetrics

class PokeQuizz : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            AndroidDevMetrics.initWith(applicationContext)
        }
    }
}
