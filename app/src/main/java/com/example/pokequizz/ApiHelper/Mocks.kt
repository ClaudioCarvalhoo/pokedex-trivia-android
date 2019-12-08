package com.example.pokequizz.ApiHelper

import com.example.pokequizz.ApiHelper.Entities.*

object Mocks {
    var alternatives = arrayOf(
        Alternative(
            "1",
            "Umbreon",
            "https://bit.ly/2P2vnuK"
        )
    )
    val questions = arrayOf(
        Question(
            "1",
            "Which of these pokémon is a pure dark-type?",
            alternatives
        )
    )

    var categories = arrayOf(
        Category("Pokémon types", arrayOf())
    )

    var leaderboard = arrayOf(
        Entry("tatinho150", 3)
    )

    var rooms = arrayOf(
        Room(
            "1",
            questions,
            categories,
            leaderboard
        ),
        Room(
            "2",
            questions,
            categories,
            leaderboard
        )
    )
}