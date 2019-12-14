package com.example.pokequizz.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class QuestionsViewModel : ViewModel() {

    private val _stem = MutableLiveData<String>()

    val stem: LiveData<String> = Transformations.map(_stem) {
        it
    }

    fun setStem(stem: String) {
        _stem.value = stem
    }
}