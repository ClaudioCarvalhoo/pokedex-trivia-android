package com.example.pokequizz.ApiHelper.Entities

class Summary(id: String, categories: Array<Category>) {
    var id: String
    var categories: Array<Category>

    init {
        this.id = id
        this.categories = categories
    }
}