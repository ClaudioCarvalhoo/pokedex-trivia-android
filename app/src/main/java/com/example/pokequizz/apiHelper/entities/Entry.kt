package com.example.pokequizz.apiHelper.entities

import java.io.Serializable

class Entry(username: String, score: Int) : Serializable {
    var username: String
    var score: Int

    init {
        this.username = username
        this.score = score
    }
}
