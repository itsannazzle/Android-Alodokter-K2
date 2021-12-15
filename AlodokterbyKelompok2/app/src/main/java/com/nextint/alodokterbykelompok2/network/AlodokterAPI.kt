package com.nextint.alodokterbykelompok2.network

import com.nextint.alodokterbykelompok2.data.remote.response.article.ArticleResponse
import com.nextint.alodokterbykelompok2.model.CreateUserResponse
import retrofit2.Call
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

    @GET("articles")
    fun getArticle() : Call<ArticleResponse>

    @GET()
    suspend fun getUserData(){

    }
    @POST()
    suspend fun postUserLogin(){

    }
}