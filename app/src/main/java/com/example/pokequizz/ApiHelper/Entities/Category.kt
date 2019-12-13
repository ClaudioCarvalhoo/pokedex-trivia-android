package com.example.pokequizz.ApiHelper.Entities

class Category(id: String, name: String, subcategories: Array<Category>) {
    var id: String
    var name: String
    var subcategories: Array<Category>

    init {
        this.id = id
        this.name = name
        this.subcategories = subcategories
    }
}