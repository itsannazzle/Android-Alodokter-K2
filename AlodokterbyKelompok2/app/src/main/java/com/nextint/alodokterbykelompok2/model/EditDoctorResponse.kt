package com.nextint.alodokterbykelompok2.model

import com.google.gson.annotations.SerializedName

data class EditDoctorResponse(
    @field:SerializedName("data")
    val data: DoctorResponse? = null,

    @field:SerializedName("meta")
    val meta: Status? = null,
)