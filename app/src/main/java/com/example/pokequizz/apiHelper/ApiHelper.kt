package com.example.pokequizz.apiHelper

import com.example.pokequizz.apiHelper.entities.Answer
import com.example.pokequizz.apiHelper.entities.Category
import com.example.pokequizz.apiHelper.entities.CreateRoomRequest
import com.example.pokequizz.apiHelper.entities.Entry
import com.example.pokequizz.apiHelper.entities.Room
import com.example.pokequizz.apiHelper.entities.Summary
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiHelper {
    @GET("/rooms/{id}")
    fun getRoom(@Path("id") id: String): Call<Room>

    @GET("/rooms/summary")
    fun getRooms(): Call<List<Summary>>

    @GET("/categories")
    fun getCategories(): Call<List<Category>>

    @POST("/rooms")
    fun createRoom(@Body createRoomRequest: CreateRoomRequest): Call<String>

    @POST("/rooms/{id}/answer")
    fun submitAnswers(@Path("id") id: String, @Body submitAnswer: List<Answer>): Call<List<Entry>>
}
