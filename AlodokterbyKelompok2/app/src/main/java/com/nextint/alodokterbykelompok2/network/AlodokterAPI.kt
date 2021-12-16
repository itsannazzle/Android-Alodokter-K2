package com.nextint.alodokterbykelompok2.network

import com.nextint.alodokterbykelompok2.data.remote.response.article.ArticleResponse
import com.nextint.alodokterbykelompok2.data.remote.response.doctor.DoctorResponse
import com.nextint.alodokterbykelompok2.model.CreateUserResponse
import com.nextint.alodokterbykelompok2.model.LoginRequest
import com.nextint.alodokterbykelompok2.model.LoginResponse
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

    @GET("doctors")
    fun getDoctor() : Call<DoctorResponse>

    @GET()
    suspend fun getUserData(){

    }

    @POST("/auth/login")
    suspend fun postUserLogin(
        @Body loginRequest: LoginRequest
        ) : LoginResponse
}