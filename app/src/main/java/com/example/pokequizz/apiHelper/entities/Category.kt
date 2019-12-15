package com.example.pokequizz.apiHelper.entities

class Category(id: String, name: String, subcategories: List<Category>) {
    var id: String
    var name: String
    var subcategories: List<Category>

    init {
        this.id = id
        this.name = name
        this.subcategories = subcategories
    }
}
