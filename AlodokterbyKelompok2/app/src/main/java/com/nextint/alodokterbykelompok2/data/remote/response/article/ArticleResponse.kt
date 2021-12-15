package com.nextint.alodokterbykelompok2.data.remote.response.article

import com.google.gson.annotations.SerializedName

data class ArticleResponse(@SerializedName("data")
                           val data: List<Article>?,
                           @SerializedName("page")
                           val page: String = "",
                           @SerializedName("message")
                           val message: String = "")