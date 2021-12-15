package com.nextint.alodokterbykelompok2.data

import androidx.lifecycle.LiveData
import com.nextint.alodokterbykelompok2.data.local.ArticleEntity
import com.nextint.alodokterbykelompok2.data.local.DoctorEntity

interface AloDataSource {
    fun getArticles(): LiveData<List<ArticleEntity>>
    fun getDoctors(): LiveData<List<DoctorEntity>>
}