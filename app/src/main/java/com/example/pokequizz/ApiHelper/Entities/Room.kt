package com.example.pokequizz.ApiHelper.Entities

class Room(id: String, questions: Array<Question>, categories: Array<Category>, leaderboard: Array<Entry>) {
    var id: String
    var questions: Array<Question>
    var categories: Array<Category>
    var leaderboard: Array<Entry>

    init {
        this.id = id
        this.questions = questions
        this.categories = categories
        this.leaderboard = leaderboard
    }
}