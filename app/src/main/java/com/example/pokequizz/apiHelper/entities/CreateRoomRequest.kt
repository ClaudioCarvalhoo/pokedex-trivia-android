package com.example.pokequizz.apiHelper.entities

class CreateRoomRequest(numberOfQuestions: Int, categories: List<String>) {

    var numberOfQuestions: Int
    var categories: List<String>

    init {
        this.numberOfQuestions = numberOfQuestions
        this.categories = categories
    }
}
