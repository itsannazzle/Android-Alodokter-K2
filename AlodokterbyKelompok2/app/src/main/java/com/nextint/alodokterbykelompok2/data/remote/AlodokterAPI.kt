package com.nextint.alodokterbykelompok2.data.remote

import com.nextint.alodokterbykelompok2.model.*
import retrofit2.http.*

interface AlodokterAPI {
    @POST("/users")
    suspend fun createAccount(
        @Body dataUser: CreateUserResponse
    ): CreateUserResponse

    @GET("/users")
    suspend fun getUserData(
        @Path("id") idUser: Int
    ): CreateUserResponse

    @GET()
    suspend fun getArticle() {

    }

    @POST()
    suspend fun postUserLogin() {

    }

    @GET("/users/{username}")
    suspend fun getUser(
        @Header(value = "Authorization") authorization: String,
        @Path(value = "username") username: String
    ): UserResponse

    @FormUrlEncoded
    @POST("/register")
    suspend fun completeUserData(@FieldMap request: Map<String, String>): UserResponse

    @FormUrlEncoded
    @PUT("/users/{username}")
    suspend fun editUserData(
        @Header(value = "Authorization") authorization: String,
        @Path(value = "username") username: String,
        @FieldMap request: Map<String, String>
    ): EditUserResponse

    //   [START] Doctor

    @GET("/doctors/{nip}")
    suspend fun getDetailDoctor(@Path(value = "nip") nip: String): DoctorResponse

    @FormUrlEncoded
    @POST("/doctors")
    suspend fun completeDoctorData(@FieldMap request: Map<String, String>): DoctorResponse

    @FormUrlEncoded
    @PUT("/doctors/{nip}")
    suspend fun editDoctorData(
        @Path(value = "nip") nip: String,
        @FieldMap request: Map<String, String>
    ): EditDoctorResponse

    //   [END] Doctor

}