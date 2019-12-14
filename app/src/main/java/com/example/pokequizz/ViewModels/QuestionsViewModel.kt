package com.example.pokequizz.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class QuestionsViewModel : ViewModel() {

    private val _stem = MutableLiveData<String>()
    private val _imageUrl = MutableLiveData<String>()

    val stem: LiveData<String> = Transformations.map(_stem) {
        it
    }

    val imageUrl: LiveData<String> = Transformations.map(_imageUrl) {
        it
    }

    fun setStem(stem: String) {
        _stem.value = stem
    }

    fun setImageUrl(imageUrl: String) {
        _imageUrl.value = imageUrl
    }
}