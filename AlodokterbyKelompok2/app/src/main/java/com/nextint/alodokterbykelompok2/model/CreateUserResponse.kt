package com.nextint.alodokterbykelompok2.model

import com.google.gson.annotations.SerializedName

data class CreateUserResponse(

	@field:SerializedName("place_of_birth")
	val placeOfBirth: String? = null,

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("gender")
	val gender: String? = null,

	@field:SerializedName("phone")
	val phone: Int? = null,

	@field:SerializedName("date_of_birth")
	val dateOfBirth: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("email")
	val email: String? = null
)
