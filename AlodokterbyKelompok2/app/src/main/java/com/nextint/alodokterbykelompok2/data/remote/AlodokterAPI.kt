package com.nextint.alodokterbykelompok2.data.remote

import com.nextint.alodokterbykelompok2.model.CreateUserResponse
import com.nextint.alodokterbykelompok2.model.LoginResponse
import retrofit2.http.*

interface AlodokterAPI {
    @POST("/register")
    suspend fun createAccount(
        @Body dataUser : CreateUserResponse
    ) : CreateUserResponse

    @GET("/users")
    suspend fun getUserData(
        @Path("id") idUser : Int
    ) : CreateUserResponse

    @GET()
    suspend fun getArticle(){

    }
    @GET()
    suspend fun getUserData(){

    }
    @FormUrlEncoded
    @POST("/auth/login")
    suspend fun postUserLogin(
        @Field("email") email : String,
        @Field("password") password : String
        ) : LoginResponse
}