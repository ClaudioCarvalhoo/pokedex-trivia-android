package com.example.pokequizz.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.pokequizz.apiHelper.entities.Alternative

class QuestionsViewModel : ViewModel() {

    private val _id = MutableLiveData<String>()
    private val _stem = MutableLiveData<String>()
    private val _imageUrl = MutableLiveData<String>()
    private val _alternatives = MutableLiveData<List<Alternative>>()

    var id: LiveData<String> = Transformations.map(_id) {
        it
    }

    val stem: LiveData<String> = Transformations.map(_stem) {
        it
    }

    val imageUrl: LiveData<String> = Transformations.map(_imageUrl) {
        it
    }

    val alternatives: LiveData<List<Alternative>> = Transformations.map(_alternatives) {
        it
    }

    fun setId(id: String) {
        _id.value = id
    }

    fun setStem(stem: String) {
        _stem.value = stem
    }

    fun setImageUrl(imageUrl: String) {
        _imageUrl.value = imageUrl
    }

    fun setAlternatives(alternatives: List<Alternative>) {
        _alternatives.value = alternatives
    }
}
