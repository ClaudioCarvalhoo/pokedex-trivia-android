package com.example.pokequizz.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class RoomInfoViewModel : ViewModel() {

    private val _qtdOfQuestions = MutableLiveData<Int>()
    private val _roomId = MutableLiveData<String>()

    val qtdOfQuestions: LiveData<String> = Transformations.map(_qtdOfQuestions) {
        "$it"
    }

    val roomId: LiveData<String> = Transformations.map(_roomId) {
        it
    }

    fun setQtdOfQuestions(qtdOfQuestions: Int) {
        _qtdOfQuestions.value = qtdOfQuestions
    }

    fun setRoomId(roomId: String) {
        _roomId.value = roomId
    }
}