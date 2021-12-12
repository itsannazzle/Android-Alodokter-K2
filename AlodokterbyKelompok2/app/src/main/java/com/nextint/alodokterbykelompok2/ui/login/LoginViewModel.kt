package com.nextint.alodokterbykelompok2.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nextint.alodokterbykelompok2.model.CreateUserResponse
import com.nextint.alodokterbykelompok2.model.LoginResponse
import com.nextint.alodokterbykelompok2.utils.Result
import com.nextint.alodokterbykelompok2.utils.RetrofitInstanceBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private var _dataResponse = MutableLiveData<Result<LoginResponse>>()
    val dataResponse : LiveData<Result<LoginResponse>> = _dataResponse

    fun postLogin(email : String, password : String){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = RetrofitInstanceBuilder.RETROFIT_INSTANCE.postUserLogin(email, password)
                _dataResponse.postValue(Result.Success(data))
            } catch (e : Throwable){
                _dataResponse.postValue(Result.Error(e))
            }
        }
    }
}