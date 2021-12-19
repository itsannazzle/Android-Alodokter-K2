package com.nextint.alodokterbykelompok2.ui.doctor

import androidx.lifecycle.ViewModel
import com.nextint.alodokterbykelompok2.data.AloRepository

class DoctorViewModel(private val aloRepository: AloRepository) : ViewModel() {
    fun getDoctor() = aloRepository.getDoctors()
}