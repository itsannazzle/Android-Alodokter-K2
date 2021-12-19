package com.nextint.alodokterbykelompok2.ui.forgotpassword

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nextint.alodokterbykelompok2.model.BaseResponse
import com.nextint.alodokterbykelompok2.model.NewPasswordRequest
import com.nextint.alodokterbykelompok2.utils.Result
import com.nextint.alodokterbykelompok2.utils.RetrofitInstanceBuilder
import kotlinx.coroutines.launch

class LupaPasswordViewModel : ViewModel() {
    private var _sendEmailResp = MutableLiveData<Result<BaseResponse>>()
    val sendEmailResp : LiveData<Result<BaseResponse>> = _sendEmailResp

    private var _newPasswordResp = MutableLiveData<Result<BaseResponse>>()
    val newPasswordResp : LiveData<Result<BaseResponse>> = _newPasswordResp

    fun sendToken(email : String){
        viewModelScope.launch {
            try {
                val data = RetrofitInstanceBuilder.RETROFIT_INSTANCE.sendToken(email)
                _sendEmailResp.postValue(Result.Success(data))
            } catch (e : Throwable){
                _sendEmailResp.postValue(Result.Error(e))
            }
        }
    }

    fun newPassword(newPasswordRequest: NewPasswordRequest){
        viewModelScope.launch {
            try {
                val data = RetrofitInstanceBuilder.RETROFIT_INSTANCE.newPassword(newPasswordRequest)
                _newPasswordResp.postValue(Result.Success(data))
            } catch (e : Throwable){
                _newPasswordResp.postValue(Result.Error(e))
            }
        }
    }




}