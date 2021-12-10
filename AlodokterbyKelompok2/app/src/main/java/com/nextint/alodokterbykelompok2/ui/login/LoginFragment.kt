package com.nextint.alodokterbykelompok2.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nextint.alodokterbykelompok2.R
import com.nextint.alodokterbykelompok2.databinding.FragmentLoginBinding
import com.nextint.alodokterbykelompok2.ui.createaccount.CreateAccountFragment
import com.nextint.alodokterbykelompok2.ui.homepage.HomePageActivity


class LoginFragment : Fragment() {
    private lateinit var binding : FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener(savedInstanceState)
    }

    private fun initListener(savedInstanceState: Bundle?){
        binding.btnLogin.setOnClickListener {
            startActivity(Intent(requireContext(),HomePageActivity::class.java))
        }

        binding.tvCreateAccount.setOnClickListener {
            if (savedInstanceState == null) {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.appContainer, CreateAccountFragment())
                    .addToBackStack(null)
                    .commit()
            }
        }

        binding.tvLewatkan.setOnClickListener {
            startActivity(Intent(requireContext(),HomePageActivity::class.java))
        }
    }

}