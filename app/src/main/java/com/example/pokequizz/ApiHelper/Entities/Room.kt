package com.example.pokequizz.ApiHelper.Entities

class Room(id: String, questions: List<Question>, categories: List<String>, leaderboard: Array<Entry>) {
    var id: String
    var questions: List<Question>
    var categories: List<String>
    var leaderboard: Array<Entry>

    init {
        this.id = id
        this.questions = questions
        this.categories = categories
        this.leaderboard = leaderboard
    }
}