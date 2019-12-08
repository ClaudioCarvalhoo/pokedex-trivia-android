package com.example.pokequizz.ApiHelper

import com.example.pokequizz.ApiHelper.Entities.Room

object ApiHelper {
    fun getRoom(id: String): Room? {
        return Mocks.rooms.find { it.id == id }
    }
}