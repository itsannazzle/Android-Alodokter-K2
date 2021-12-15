package com.nextint.alodokterbykelompok2.data

import androidx.lifecycle.LiveData
import com.nextint.alodokterbykelompok2.data.local.ArticleEntity

interface AloDataSource {
    fun getArticles(): LiveData<List<ArticleEntity>>
}