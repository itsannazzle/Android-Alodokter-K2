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

    @GET("articles")
    fun getArticle() : Call<ArticleResponse>

    @GET("doctors")
    fun getDoctor() : Call<DoctorResponse>

    @GET()
    suspend fun getUserData(){

    }

    @FormUrlEncoded
    @POST("/password/forgot")
    suspend fun sendToken(
        @Field("email") email : String
    ) : BaseResponse

    @POST("/password/reset")
    suspend fun newPassword(
        @Body newPasswordRequest: NewPasswordRequest
    ) : BaseResponse




    @POST("/auth/login")
    suspend fun postUserLogin(
        @Body loginRequest: LoginRequest
        ) : LoginResponse
}