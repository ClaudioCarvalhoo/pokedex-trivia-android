package com.example.pokequizz.apiHelper

class RetrofitFacade {
    companion object {
        val retrofit = RetrofitInitializer().apiHelper()
    }
}
