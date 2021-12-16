package com.nextint.alodokterbykelompok2.data.remote

import android.util.Log
import com.nextint.alodokterbykelompok2.data.remote.response.article.Article
import com.nextint.alodokterbykelompok2.data.remote.response.article.ArticleResponse
import com.nextint.alodokterbykelompok2.data.remote.response.doctor.Doctor
import com.nextint.alodokterbykelompok2.data.remote.response.doctor.DoctorResponse
import com.nextint.alodokterbykelompok2.network.ApiConfig
import com.nextint.alodokterbykelompok2.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource()
            }
    }

    fun getArticles(callback: LoadArticlesCallback){
        EspressoIdlingResource.increment()
        val client = ApiConfig.getArticleAPI().getArticle()
        client.enqueue(object : Callback<ArticleResponse> {
            override fun onResponse(call: Call<ArticleResponse>, response: Response<ArticleResponse>) {
                callback.onArticlesLoaded(response.body()?.data)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<ArticleResponse>, t: Throwable) {
                Log.e("RemoteDataSource", "getArticle onFailure : ${t.message}")
                EspressoIdlingResource.decrement()
            }
        })
    }

    fun getDoctors(callback: LoadDoctorsCallback){
        EspressoIdlingResource.increment()
        val client = ApiConfig.getAlodokterAPI().getDoctor()
        client.enqueue(object  : Callback<DoctorResponse>{
            override fun onResponse(call: Call<DoctorResponse>, response: Response<DoctorResponse>) {
                callback.onDoctorsLoaded(response.body()?.data)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<DoctorResponse>, t: Throwable) {
                Log.e("RemoteDataSource", "getDoctor onFailure : ${t.message}")
                EspressoIdlingResource.decrement()
            }
        })
    }

    interface LoadArticlesCallback {
        fun onArticlesLoaded(articles: List<Article>?)
    }

    interface LoadDoctorsCallback {
        fun onDoctorsLoaded(doctors: List<Doctor>?)
    }
}