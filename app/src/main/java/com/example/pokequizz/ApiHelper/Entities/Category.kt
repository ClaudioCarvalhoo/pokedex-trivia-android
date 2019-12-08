package com.example.pokequizz.ApiHelper.Entities

class Category(category: String, subcategories: Array<String>) {
    var category: String
    var subcategories: Array<String>

    init {
        this.category = category
        this.subcategories = subcategories
    }
}