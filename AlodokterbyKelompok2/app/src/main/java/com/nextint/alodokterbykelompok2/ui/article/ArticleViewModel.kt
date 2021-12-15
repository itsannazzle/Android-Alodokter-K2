package com.nextint.alodokterbykelompok2.ui.article

import androidx.lifecycle.ViewModel
import com.nextint.alodokterbykelompok2.data.AloRepository
import com.nextint.alodokterbykelompok2.data.remote.response.article.Article
import com.nextint.alodokterbykelompok2.utils.DataDummy

class ArticleViewModel(private val aloRepository: AloRepository) : ViewModel() {
    fun getArticle() = aloRepository.getArticles()
}