package com.nextint.alodokterbykelompok2.ui.createaccount

import androidx.lifecycle.*
import com.nextint.alodokterbykelompok2.model.CreateUserResponse
import com.nextint.alodokterbykelompok2.utils.Result
import com.nextint.alodokterbykelompok2.utils.RetrofitInstanceBuilder
import com.nextint.alodokterbykelompok2.utils.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateAccountViewModel : ViewModel() {
    private var _dataResponse = MutableLiveData<Result<CreateUserResponse>>()
    val dataResponse : LiveData<Result<CreateUserResponse>> = _dataResponse

    private val _loading : MutableLiveData<Boolean> = MutableLiveData(false)
    val loading : LiveData<Boolean> = _loading

    private val _message : SingleLiveEvent<String> = SingleLiveEvent()
    val message : LiveData<String> = _message

    private var _gender = MutableLiveData<String>()
    val gender : LiveData<String> = _gender

    fun postCreateAccount(dataUser : CreateUserResponse){
        viewModelScope.launch(Dispatchers.IO) {
            _loading.postValue(true)
            try {
                val data = RetrofitInstanceBuilder.RETROFIT_INSTANCE.createAccount(dataUser)
                _dataResponse.postValue(Result.Success(data))
            } catch (e : Throwable){
                _dataResponse.postValue(Result.Error(e))
                _message.postValue(e.message)
            } finally {
                _loading.postValue(false)
            }
        }
    }

    fun setGender(gender : String?){
        _gender.postValue(gender ?: "undefined gender")
    }
}