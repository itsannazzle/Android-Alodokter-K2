package com.nextint.alodokterbykelompok2.data.remote

import com.nextint.alodokterbykelompok2.model.CreateUserResponse
import com.nextint.alodokterbykelompok2.model.LoginRequest
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

    @POST("/auth/login")
    suspend fun postUserLogin(
        @Body loginRequest: LoginRequest
        ) : LoginResponse
}