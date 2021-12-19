package com.nextint.alodokterbykelompok2.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("exp")
	val exp: String,

	@field:SerializedName("token")
	val token: String,

	@field:SerializedName("username")
	val username: String
)

data class LoginRequest(
	val email : String,
	val password : String
)