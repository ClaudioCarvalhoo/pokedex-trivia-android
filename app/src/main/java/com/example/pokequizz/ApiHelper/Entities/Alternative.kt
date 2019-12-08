package com.example.pokequizz.ApiHelper.Entities

class Alternative(id: String, text: String, imageUrl: String) {
    var id: String
    var text: String
    var imageUrl: String

    init {
        this.id = id
        this.text = text
        this.imageUrl = imageUrl
    }
}