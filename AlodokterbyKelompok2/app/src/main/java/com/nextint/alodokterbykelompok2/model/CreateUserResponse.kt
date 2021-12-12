package com.nextint.alodokterbykelompok2.model

import com.google.gson.annotations.SerializedName

data class CreateUserResponse(

	@field:SerializedName("birthday")
	val birthday: String,

	@field:SerializedName("gender")
	val gender: String,

	@field:SerializedName("phone")
	val phone: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("password")
	val password: String,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("username")
	val username: String
)
