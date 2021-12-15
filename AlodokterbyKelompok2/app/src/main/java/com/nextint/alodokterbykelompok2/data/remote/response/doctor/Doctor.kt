package com.nextint.alodokterbykelompok2.data.remote.response.doctor

import com.google.gson.annotations.SerializedName

data class Doctor(
    @SerializedName("img") var img: String? = "",
    @SerializedName("nip") var nip: String? = "",
    @SerializedName("updated_at") var updatedAt: String? = "",
    @SerializedName("phone") var phone: String? = "",
    @SerializedName("name") var name: String? = "",
    @SerializedName("created_at") var createdAt: String? = "",
    @SerializedName("spesialis") var spesialis: String? = "",
    @SerializedName("location") var location: String? = "",
    @SerializedName("id") var id: Int = 0,
    @SerializedName("timetable") var timetable: String? = "",
    @SerializedName("desc") var desc: String? = "")