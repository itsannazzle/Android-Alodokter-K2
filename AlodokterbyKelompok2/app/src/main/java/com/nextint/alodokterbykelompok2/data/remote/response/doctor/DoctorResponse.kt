package com.nextint.alodokterbykelompok2.data.remote.response.doctor

import com.google.gson.annotations.SerializedName

data class DoctorResponse(
    @SerializedName("data") val data: List<Doctor>?)