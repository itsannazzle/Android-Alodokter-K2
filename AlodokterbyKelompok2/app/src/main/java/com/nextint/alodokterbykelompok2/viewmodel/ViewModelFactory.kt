package com.nextint.alodokterbykelompok2.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nextint.alodokterbykelompok2.data.AloRepository
import com.nextint.alodokterbykelompok2.di.Injection
import com.nextint.alodokterbykelompok2.ui.article.ArticleViewModel
import com.nextint.alodokterbykelompok2.ui.doctor.DoctorViewModel

class ViewModelFactory private constructor(private val aloRepository: AloRepository) : ViewModelProvider.NewInstanceFactory(){
    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(ArticleViewModel::class.java) -> {
                ArticleViewModel(aloRepository) as T
            }
            modelClass.isAssignableFrom(DoctorViewModel::class.java) -> {
                DoctorViewModel(aloRepository) as T
            }
//            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
//                DetailViewModel(movieCatalogueRepository) as T
//            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }
}