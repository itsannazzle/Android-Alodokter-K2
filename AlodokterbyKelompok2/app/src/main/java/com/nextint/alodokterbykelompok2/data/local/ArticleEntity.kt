package com.nextint.alodokterbykelompok2.data.local

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArticleEntity(
    @SerializedName("reference") var reference: String? = "",
    @SerializedName("image") var image: String? = "",
    @SerializedName("date_posted") var datePosted: String? = "",
    @SerializedName("description") var description: String? = "",
    @SerializedName("id") var id: Int = 0,
    @SerializedName("title") var title: String? = ""
) : Parcelable