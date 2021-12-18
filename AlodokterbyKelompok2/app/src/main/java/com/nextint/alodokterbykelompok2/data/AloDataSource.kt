package com.nextint.alodokterbykelompok2.data

import androidx.lifecycle.LiveData
import com.nextint.alodokterbykelompok2.data.local.ArticleEntity
import com.nextint.alodokterbykelompok2.data.local.DetailArticleEntity
import com.nextint.alodokterbykelompok2.data.local.DoctorEntity

interface AloDataSource {
    fun getArticles(): LiveData<List<ArticleEntity>>
    fun getDetailArticle(articleId: String) : LiveData<DetailArticleEntity>
    fun getSearchArticle(articleTitle: String): LiveData<List<ArticleEntity>>
    fun getDoctors(): LiveData<List<DoctorEntity>>
    //fun getDetailDoctor(doctorId: String) : LiveData<DoctorEntity>
}