package com.nextint.alodokterbykelompok2.data.remote

import retrofit2.http.GET
import retrofit2.http.POST

interface AlodokterAPI {
    @POST()
    suspend fun createAccount(){

    }
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