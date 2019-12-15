package com.example.pokequizz.apiHelper.entities

class Room(id: String, questions: List<Question>, categories: List<String>, leaderboard: List<Entry>) {
    var id: String
    var questions: List<Question>
    var categories: List<String>
    var leaderboard: List<Entry>

    init {
        this.id = id
        this.questions = questions
        this.categories = categories
        this.leaderboard = leaderboard
    }
}
