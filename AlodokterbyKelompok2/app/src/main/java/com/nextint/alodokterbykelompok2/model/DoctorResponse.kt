package com.nextint.alodokterbykelompok2.model

import com.google.gson.annotations.SerializedName

data class DoctorResponse(

    @field:SerializedName("id")
    val placeOfBirth: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("nip")
    val nip: String? = null,

    @field:SerializedName("spesialis")
    val spesialis: String? = null,

    @field:SerializedName("location")
    val location: Int? = null,

    @field:SerializedName("timetable")
    val timetable: String? = null,

    @field:SerializedName("phone")
    val phone: String? = null,

    @field:SerializedName("img")
    val img: String? = null,

    @field:SerializedName("desc")
    val desc: String? = null,

    @field:SerializedName("created_at")
    val createdAt: String? = null,

    @field:SerializedName("updated_at")
    val updatedAt: String? = null,
)