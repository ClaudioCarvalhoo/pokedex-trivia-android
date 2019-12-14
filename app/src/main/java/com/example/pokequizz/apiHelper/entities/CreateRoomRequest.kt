package com.example.pokequizz.apiHelper.entities

class CreateRoomRequest(numberOfQuestions: Int, categories: List<Category>) {

    var numberOfQuestions: Int
    var categories: List<Category>

    init {
        this.numberOfQuestions = numberOfQuestions
        this.categories = categories
    }
}
