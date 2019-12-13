package com.example.pokequizz.ApiHelper

class RetrofitFacade {
    companion object {
        val retrofit = RetrofitInitializer().apiHelper()
    }
}