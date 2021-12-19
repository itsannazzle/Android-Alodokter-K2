package com.nextint.alodokterbykelompok2.ui.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nextint.alodokterbykelompok2.R
import com.nextint.alodokterbykelompok2.databinding.FragmentOnboarding2Binding
import com.nextint.alodokterbykelompok2.ui.login.LoginFragment


class Onboarding2Fragment : Fragment() {
    private lateinit var binding : FragmentOnboarding2Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnboarding2Binding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       initListener(savedInstanceState)
    }

    private fun initListener(savedInstanceState: Bundle?){
        binding.materialButton.setOnClickListener {
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