package com.example.pokequizz.ApiHelper

import com.example.pokequizz.ApiHelper.Entities.*

object ApiHelper {
    fun getRoom(id: String): Room? {
        return Mocks.rooms.find { it.id == id }
    }

    fun getRooms(): List<Summary> {
        return Mocks.summaries
    }

    fun getCategories(): Array<Category> {
        return Mocks.categories
    }

    fun createRoom(numberOfQuestions: String, categories: Array<Category>): String {
        return Mocks.rooms[0].id
    }

    fun submitAnswers(id: String, answers: Array<Answer>): Array<Entry> {
        return Mocks.leaderboard
    }
}