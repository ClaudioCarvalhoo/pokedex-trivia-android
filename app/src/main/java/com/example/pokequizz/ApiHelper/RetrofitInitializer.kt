package com.example.pokequizz.ApiHelper

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {

    private val BASE_URL = "https://pokedex-trivia-api.herokuapp.com"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun apiHelper() = retrofit.create(ApiHelper::class.java)
}