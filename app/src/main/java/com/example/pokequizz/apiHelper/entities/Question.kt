package com.example.pokequizz.apiHelper.entities

import java.io.Serializable

class Question(id: String, stem: String, imageUrl: String, alternatives: List<Alternative>) : Serializable {
    val id = id
    val stem = stem
    val imageUrl = imageUrl
    val alternatives = alternatives
}
