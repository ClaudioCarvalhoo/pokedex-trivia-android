package com.example.pokequizz.ApiHelper.Entities

class Room(id: String, questions: Array<Question>, categories: List<String>, leaderboard: Array<Entry>) {
    var id: String
    var questions: Array<Question>
    var categories: List<String>
    var leaderboard: Array<Entry>

    init {
        this.id = id
        this.questions = questions
        this.categories = categories
        this.leaderboard = leaderboard
    }
}