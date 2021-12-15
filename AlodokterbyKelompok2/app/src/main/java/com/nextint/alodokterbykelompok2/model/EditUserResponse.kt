package com.nextint.alodokterbykelompok2.model

import com.google.gson.annotations.SerializedName

data class EditUserResponse(
    @field:SerializedName("data")
    val data: CreateUserResponse? = null,

    @field:SerializedName("meta")
    val meta: Status? = null,
)