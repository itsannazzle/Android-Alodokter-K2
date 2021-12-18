package com.nextint.alodokterbykelompok2.ui.article

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.nextint.alodokterbykelompok2.data.AloRepository
import com.nextint.alodokterbykelompok2.data.local.ArticleEntity
import com.nextint.alodokterbykelompok2.data.local.DetailArticleEntity

class ArticleViewModel(private val aloRepository: AloRepository) : ViewModel() {
    private lateinit var detailData: LiveData<DetailArticleEntity>
    private lateinit var resultSearchArticle: LiveData<List<ArticleEntity>>

    fun getArticle() = aloRepository.getArticles()
    fun setDetail(id: String){
        detailData = aloRepository.getDetailArticle(id)
    }
    fun getDetail() = detailData
    fun setParamSearch(title: String) {resultSearchArticle = aloRepository.getSearchArticle(title)}
    fun getSearchArticle() = resultSearchArticle
}