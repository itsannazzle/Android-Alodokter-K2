package com.nextint.alodokterbykelompok2.data.remote

import com.nextint.alodokterbykelompok2.data.remote.response.article.ArticleDetailResponse
import com.nextint.alodokterbykelompok2.data.remote.response.article.ArticleResponse
import com.nextint.alodokterbykelompok2.data.remote.response.article.SearchArticleResponse
import com.nextint.alodokterbykelompok2.data.remote.response.doctor.DoctorResponse
import com.nextint.alodokterbykelompok2.model.*
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

    @GET("api/v1/articles")
    fun getArticle() : Call<ArticleResponse>

    @GET("api/v1/articles/{id}")
    fun getArticleDetail(
        @Path("id") id: String
    ) : Call<ArticleDetailResponse>

    @GET("api/v1/searcharticles/{title}")
    fun getArticleByTitle(
        @Path("title") title: String
    ) : Call<SearchArticleResponse>

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