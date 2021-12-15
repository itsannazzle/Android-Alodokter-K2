package com.nextint.alodokterbykelompok2.data.remote.response.article

import com.google.gson.annotations.SerializedName

data class Article(
    @SerializedName("reference")
    var reference: String = "",
    @SerializedName("image")
    var image: String = "",
    @SerializedName("date_posted")
    var datePosted: String = "",
    @SerializedName("updated_at")
    var updatedAt: String = "",
    @SerializedName("description")
    var description: String = "",
    @SerializedName("created_at")
    var createdAt: String = "",
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("title")
    var title: String = ""
)