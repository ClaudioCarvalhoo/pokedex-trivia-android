package com.example.pokequizz.apiHelper.entities

import java.io.Serializable

class Answer(questionId: String, choice: String) : Serializable {
    var questionId: String
    var choice: String

    init {
        this.questionId = questionId
        this.choice = choice
    }
}
