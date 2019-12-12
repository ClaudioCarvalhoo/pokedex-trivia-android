package com.example.pokequizz.ApiHelper

import com.example.pokequizz.ApiHelper.Entities.*

object ApiHelper {
    fun getRoom(id: String): Room? {
        Thread.sleep(1_000)
        return Mocks.rooms.find { it.id == id }
    }

    fun getRooms(): List<Summary> {
        Thread.sleep(1_000)
        return Mocks.summaries
    }

    fun getCategories(): Array<Category> {
        Thread.sleep(1_000)
        return Mocks.categories
    }

    fun createRoom(numberOfQuestions: String, categories: Array<Category>): String {
        Thread.sleep(1_000)
        return Mocks.rooms[0].id
    }

    fun submitAnswers(id: String, answers: Array<Answer>): Array<Entry> {
        Thread.sleep(1_000)
        return Mocks.leaderboard
    }
}