package com.nextint.alodokterbykelompok2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.nextint.alodokterbykelompok2.ui.homepage.HomeFragment
import com.nextint.alodokterbykelompok2.ui.homepage.HomePageActivity
import com.nextint.alodokterbykelompok2.ui.onboarding.Onboarding1Fragment
import com.nextint.alodokterbykelompok2.ui.onboarding.ViewPagerOnBoardingFragment
import com.nextint.alodokterbykelompok2.utils.SessionManager
import com.nextint.alodokterbykelompok2.utils.SessionRepository
import java.net.URLClassLoader.newInstance

class MainActivity : AppCompatActivity() {
    lateinit var sessionRepository: SessionRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_AlodokterByKelompok2)
        val sesi = SessionManager(this)
        sessionRepository = SessionRepository.getInstance(sesi)

        if (sessionRepository.isUserLogin()) {
            moveToHomeActivity()
        } else{
            supportFragmentManager.beginTransaction()
                .replace(R.id.appContainer, ViewPagerOnBoardingFragment())
                .commitNow()
        }

        setContentView(R.layout.activity_main)
    }



    private fun moveToHomeActivity() {
        startActivity(Intent(this, HomePageActivity::class.java))
        finish()
    }
}