package com.nextint.alodokterbykelompok2.ui.article

import androidx.lifecycle.ViewModel
import com.nextint.alodokterbykelompok2.model.Article
import com.nextint.alodokterbykelompok2.utils.DataDummy

class ArticleViewModel : ViewModel() {
    fun getArticle() : List<Article> = DataDummy.generateDummyArticle()
}