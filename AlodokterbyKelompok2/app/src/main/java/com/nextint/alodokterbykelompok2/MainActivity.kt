package com.nextint.alodokterbykelompok2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.nextint.alodokterbykelompok2.ui.onboarding.Onboarding1Fragment
import java.net.URLClassLoader.newInstance

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_AlodokterByKelompok2)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.appContainer, Onboarding1Fragment())
                .commitNow()
        }
        setContentView(R.layout.activity_main)
    }
}