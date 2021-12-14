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

    private val _loading : MutableLiveData<Boolean> = MutableLiveData(false)
    val loading : LiveData<Boolean> = _loading

    fun postLogin(email : String, password : String){
        _loading.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = RetrofitInstanceBuilder.RETROFIT_INSTANCE.postUserLogin(email, password)
                _dataResponse.postValue(Result.Success(data))
            } catch (e : Throwable){
                _dataResponse.postValue(Result.Error(e))
            } finally {
                _loading.postValue(false)
            }
        }
    }
}