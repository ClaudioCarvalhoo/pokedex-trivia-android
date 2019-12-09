package com.example.pokequizz.ApiHelper.Entities

class Answer(questionId: String, choice: String) {
    var questionId: String
    var choice: String

    init {
        this.questionId = questionId
        this.choice = choice
    }
}