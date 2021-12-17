package com.nextint.alodokterbykelompok2.ui.article

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.nextint.alodokterbykelompok2.data.AloRepository
import com.nextint.alodokterbykelompok2.data.local.DetailArticleEntity

class ArticleViewModel(private val aloRepository: AloRepository) : ViewModel() {
    private lateinit var detailData: LiveData<DetailArticleEntity>

    fun setDetail(id: String){
        detailData = aloRepository.getDetailArticle(id)
    }
    fun getArticle() = aloRepository.getArticles()
    fun getDetail() = detailData
}