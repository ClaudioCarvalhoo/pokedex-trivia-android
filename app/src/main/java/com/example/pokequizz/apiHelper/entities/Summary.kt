package com.example.pokequizz.apiHelper.entities

class Summary(id: String, categories: Array<Category>) {
    var id: String
    var categories: Array<Category>

    init {
        this.id = id
        this.categories = categories
    }
}
