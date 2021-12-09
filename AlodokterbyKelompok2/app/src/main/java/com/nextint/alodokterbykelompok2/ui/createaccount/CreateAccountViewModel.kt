package com.nextint.alodokterbykelompok2.ui.createaccount

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nextint.alodokterbykelompok2.model.CreateUserResponse
import com.nextint.alodokterbykelompok2.utils.Result
import com.nextint.alodokterbykelompok2.utils.RetrofitInstanceBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateAccountViewModel : ViewModel() {
    private var _dataResponse = MutableLiveData<Result<CreateUserResponse>>()
    val dataResponse : LiveData<Result<CreateUserResponse>> = _dataResponse


    fun postCreateAccount(dataUser : CreateUserResponse){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = RetrofitInstanceBuilder.RETROFIT_INSTANCE.createAccount(dataUser)
                _dataResponse.postValue(Result.Success(data))
            } catch (e : Throwable){
                _dataResponse.postValue(Result.Error(e))
            }
        }
    }
}