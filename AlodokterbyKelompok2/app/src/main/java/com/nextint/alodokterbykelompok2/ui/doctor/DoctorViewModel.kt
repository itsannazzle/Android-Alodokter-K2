package com.nextint.alodokterbykelompok2.ui.doctor

import androidx.lifecycle.ViewModel
import com.nextint.alodokterbykelompok2.model.Doctor
import com.nextint.alodokterbykelompok2.utils.DataDummy

class DoctorViewModel : ViewModel() {
    fun getDoctor(): List<Doctor> = DataDummy.generateDummyDoctor()
}