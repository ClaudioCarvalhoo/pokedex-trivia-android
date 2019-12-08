package com.example.pokequizz.ApiHelper.Entities

class Question(id: String, stem: String, alternatives: Array<Alternative>) {
    var id: String
    var stem: String
    var alternatives: Array<Alternative>

    init {
        this.id = id
        this.stem = stem
        this.alternatives = alternatives
    }
}