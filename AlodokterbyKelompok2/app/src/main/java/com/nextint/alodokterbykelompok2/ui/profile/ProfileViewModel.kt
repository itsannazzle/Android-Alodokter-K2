package com.nextint.alodokterbykelompok2.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nextint.alodokterbykelompok2.model.DoctorResponse
import com.nextint.alodokterbykelompok2.model.EditDoctorResponse
import com.nextint.alodokterbykelompok2.model.EditUserResponse
import com.nextint.alodokterbykelompok2.model.UserResponse
import com.nextint.alodokterbykelompok2.utils.Result
import com.nextint.alodokterbykelompok2.utils.RetrofitInstanceBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
    private var _profileResponse = MutableLiveData<Result<UserResponse>>()
    private var _editProfileResponse = MutableLiveData<Result<EditUserResponse>>()

    private var _doctorResponse = MutableLiveData<Result<DoctorResponse>>()
    private var _editDoctorResponse = MutableLiveData<Result<EditDoctorResponse>>()

    val profileResponse: LiveData<Result<UserResponse>> = _profileResponse
    val editProfileResponse: LiveData<Result<EditUserResponse>> = _editProfileResponse

    val doctorResponse: LiveData<Result<DoctorResponse>> = _doctorResponse
    val editDoctorResponse: LiveData<Result<EditDoctorResponse>> = _editDoctorResponse

    fun getProfile(authorization: String, username: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data =
                    RetrofitInstanceBuilder.RETROFIT_INSTANCE.getUser(authorization, username)
                _profileResponse.postValue(Result.Success(data))
            } catch (e: Throwable) {
                _profileResponse.postValue(Result.Error(e))
            }
        }
    }

    fun editProfile(authorization: String, username: String, request: Map<String, String>) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data =
                    RetrofitInstanceBuilder.RETROFIT_INSTANCE.editUserData(
                        authorization,
                        username,
                        request
                    )
                _editProfileResponse.postValue(Result.Success(data))
            } catch (e: Throwable) {
                _editProfileResponse.postValue(Result.Error(e))
            }
        }
    }

    fun registerProfile(request: Map<String, String>) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = RetrofitInstanceBuilder.RETROFIT_INSTANCE.completeUserData(request)
                _profileResponse.postValue(Result.Success(data))
            } catch (e: Throwable) {
                _profileResponse.postValue(Result.Error(e))
            }
        }
    }

    fun getDoctor(nip: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data =
                    RetrofitInstanceBuilder.RETROFIT_INSTANCE.getDetailDoctor(nip)
                _doctorResponse.postValue(Result.Success(data))
            } catch (e: Throwable) {
                _doctorResponse.postValue(Result.Error(e))
            }
        }
    }

    fun editDoctor(nip: String, request: Map<String, String>) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data =
                    RetrofitInstanceBuilder.RETROFIT_INSTANCE.editDoctorData(
                        nip, request
                    )
                _editDoctorResponse.postValue(Result.Success(data))
            } catch (e: Throwable) {
                _editDoctorResponse.postValue(Result.Error(e))
            }
        }
    }

    fun registerDoctor(request: Map<String, String>) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = RetrofitInstanceBuilder.RETROFIT_INSTANCE.completeDoctorData(request)
                _doctorResponse.postValue(Result.Success(data))
            } catch (e: Throwable) {
                _doctorResponse.postValue(Result.Error(e))
            }
        }
    }
}