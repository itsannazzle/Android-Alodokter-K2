package com.nextint.alodokterbykelompok2.data.remote

import com.nextint.alodokterbykelompok2.model.CreateUserResponse
import retrofit2.http.*

interface AlodokterAPI {
    @POST("/users")
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
    @POST()
    suspend fun postUserLogin(){

    }
}