package com.example.pokequizz.ApiHelper.Entities

class Category(category: String, subcategories: Array<Category>) {
    var category: String
    var subcategories: Array<Category>

    init {
        this.category = category
        this.subcategories = subcategories
    }
}