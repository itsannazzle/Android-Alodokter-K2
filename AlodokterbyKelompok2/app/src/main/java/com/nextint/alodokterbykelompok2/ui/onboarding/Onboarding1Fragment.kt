package com.nextint.alodokterbykelompok2.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nextint.alodokterbykelompok2.R
import com.nextint.alodokterbykelompok2.databinding.FragmentOnboarding1Binding
import com.nextint.alodokterbykelompok2.ui.login.LoginFragment


class Onboarding1Fragment : Fragment() {
    private lateinit var binding: FragmentOnboarding1Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOnboarding1Binding.inflate(inflater, container, false)
        activity?.supportFragmentManager?.popBackStack()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener(savedInstanceState)
    }


    private fun initListener(savedInstanceState: Bundle?) {
        binding.btnStarted.setOnClickListener {
            if (savedInstanceState == null) {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.appContainer, LoginFragment())
                    .addToBackStack(null)
                    .commit()
            }
        }
        binding.tvLewatkan.setOnClickListener {
            if (savedInstanceState == null) {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.appContainer, LoginFragment())
                    .addToBackStack(null)
                    .commit()
            }
        }
    }

}