package com.nextint.alodokterbykelompok2.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nextint.alodokterbykelompok2.utils.RetrofitInstanceBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    //private val _responseLogin = MutableLiveData

    fun postLogin(email : String, password : String){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = RetrofitInstanceBuilder.RETROFIT_INSTANCE.postUserLogin(email, password)

            } catch (e : Throwable){

            }
        }
    }
}