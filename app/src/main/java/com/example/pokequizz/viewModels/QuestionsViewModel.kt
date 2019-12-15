package com.example.pokequizz.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.pokequizz.apiHelper.entities.Alternative
import com.example.pokequizz.apiHelper.entities.Answer

class QuestionsViewModel : ViewModel() {

    private val _id = MutableLiveData<String>()
    private val _stem = MutableLiveData<String>()
    private val _imageUrl = MutableLiveData<String>()
    private val _pair = MutableLiveData<Pair<List<Alternative>, ArrayList<Answer>>>()

    var id: LiveData<String> = Transformations.map(_id) {
        it
    }

    val stem: LiveData<String> = Transformations.map(_stem) {
        it
    }

    val imageUrl: LiveData<String> = Transformations.map(_imageUrl) {
        it
    }

    val pair: LiveData<Pair<List<Alternative>, ArrayList<Answer>>> = Transformations.map(_pair) {
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

    fun setPair(alternatives: List<Alternative>, answers: ArrayList<Answer>) {
        _pair.value = Pair(alternatives, answers)
    }
}
