package com.nextint.alodokterbykelompok2.data.remote

import com.nextint.alodokterbykelompok2.model.CreateUserResponse
import com.nextint.alodokterbykelompok2.model.EditUserResponse
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
    suspend fun getUserData(
        @Header(value = "Authorization") authorization: String,
        @Path(value = "username") username: String
    ): CreateUserResponse

    @FormUrlEncoded
    @POST("/register")
    suspend fun completeUserData(@FieldMap request: Map<String, String>): CreateUserResponse

    @FormUrlEncoded
    @PUT("/users/{username}")
    suspend fun editUserData(
        @Header(value = "Authorization") authorization: String,
        @Path(value = "username") username: String,
        @FieldMap request: Map<String, String>
    ): EditUserResponse

    //   [START] Doctor

    @GET("/doctors")
    suspend fun getDoctorData(): CreateUserResponse

    @GET("/doctors/{nip}")
    suspend fun getDetailDoctor(@Path(value = "nip") nip: String): CreateUserResponse

    @FormUrlEncoded
    @POST("/doctors")
    suspend fun completeDoctorData(@FieldMap request: Map<String, String>): CreateUserResponse

    @FormUrlEncoded
    @PUT("/doctors/{nip}")
    suspend fun editDoctorData(
        @Path(value = "nip") nip: String,
        @FieldMap request: Map<String, String>
    ): CreateUserResponse

    //   [END] Doctor

}