package com.nextint.alodokterbykelompok2.model

import com.google.gson.annotations.SerializedName

data class BaseResponse(

	@field:SerializedName("status")
	val status: String? = null
)
