package com.nextint.alodokterbykelompok2.data.remote.response.article


import com.google.gson.annotations.SerializedName

data class SearchArticleResponse(
    @SerializedName("data") val data: List<Article>?,
    @SerializedName("message") val message: String = "")



