package com.nextint.alodokterbykelompok2.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nextint.alodokterbykelompok2.data.local.ArticleEntity
import com.nextint.alodokterbykelompok2.data.remote.RemoteDataSource
import com.nextint.alodokterbykelompok2.data.remote.response.article.Article
import com.nextint.alodokterbykelompok2.model.CreateUserResponse

class AloRepository private constructor(private val remoteDataSource: RemoteDataSource) : AloDataSource{
    companion object {
        @Volatile
        private var instance: AloRepository? = null
        fun getInstance(remoteData: RemoteDataSource): AloRepository =
            instance ?: synchronized(this) {
                instance ?: AloRepository(remoteData)
            }
    }

    override fun getArticles(): LiveData<List<ArticleEntity>> {
        val articleResult = MutableLiveData<List<ArticleEntity>>()

        remoteDataSource.getArticles(object : RemoteDataSource.LoadArticlesCallback{
            override fun onArticlesLoaded(articles: List<Article>?) {
                val articleList = ArrayList<ArticleEntity>()
                if (articles != null){
                    for (response in articles){
                        with(response){
                            val article = ArticleEntity(reference, image, datePosted, id, title)
                            articleList.add(article)
                        }
                    }
                    articleResult.postValue(articleList)
                }
            }
        })
        return articleResult
    }

}

