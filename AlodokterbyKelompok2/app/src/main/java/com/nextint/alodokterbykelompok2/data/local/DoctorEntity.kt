package com.nextint.alodokterbykelompok2.data.local

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DoctorEntity(
    @SerializedName("img") var img: String? = "",
    @SerializedName("name") var name: String? = "",
    @SerializedName("spesialis") var spesialis: String? = "",
    @SerializedName("id") var id: Int = 0
) : Parcelable
