package com.example.pokequizz.ApiHelper.Entities

import java.io.Serializable

class Alternative(id: String, text: String, imageUrl: String) : Serializable{
    var id: String
    var text: String
    var imageUrl: String

    init {
        this.id = id
        this.text = text
        this.imageUrl = imageUrl
    }
}