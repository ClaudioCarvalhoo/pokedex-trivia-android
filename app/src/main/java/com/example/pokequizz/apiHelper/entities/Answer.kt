package com.example.pokequizz.apiHelper.entities

class Answer(questionId: String, choice: String) {
    var questionId: String
    var choice: String

    init {
        this.questionId = questionId
        this.choice = choice
    }
}
