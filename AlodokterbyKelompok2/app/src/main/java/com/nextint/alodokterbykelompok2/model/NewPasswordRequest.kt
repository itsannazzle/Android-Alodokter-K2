package com.nextint.alodokterbykelompok2.model

import com.google.gson.annotations.SerializedName

data class NewPasswordRequest(

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("token")
	val token: String? = null
)
