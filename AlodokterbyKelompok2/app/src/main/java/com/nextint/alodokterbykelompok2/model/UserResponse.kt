package com.nextint.alodokterbykelompok2.model

import com.google.gson.annotations.SerializedName

data class UserResponse(

    @field:SerializedName("id")
    val placeOfBirth: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("birthday")
    val birthday: String? = null,

    @field:SerializedName("gender")
    val gender: String? = null,

    @field:SerializedName("phone")
    val phone: Int? = null,

    @field:SerializedName("img")
    val img: String? = null,

    @field:SerializedName("username")
    val username: String? = null,

    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("password_digest")
    val passwordDigest: String? = null,

    @field:SerializedName("created_at")
    val createdAt: String? = null,

    @field:SerializedName("updated_at")
    val updatedAt: String? = null,

    @field:SerializedName("reset_password_token")
    val resetPasswordToken: String? = null,

    @field:SerializedName("reset_password_sent_at")
    val resetPasswordSentAt: String? = null
)
