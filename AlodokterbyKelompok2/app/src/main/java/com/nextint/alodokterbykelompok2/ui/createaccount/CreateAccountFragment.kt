package com.nextint.alodokterbykelompok2.ui.createaccount

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.nextint.alodokterbykelompok2.R
import com.nextint.alodokterbykelompok2.databinding.FragmentCreateAccountBinding
import com.nextint.alodokterbykelompok2.model.CreateUserResponse
import com.nextint.alodokterbykelompok2.ui.login.LoginFragment


class CreateAccountFragment : Fragment() {
    private lateinit var binding: FragmentCreateAccountBinding
    private val viewModel: CreateAccountViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateAccountBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener(savedInstanceState)
    }

    private fun initListener(savedInstanceState: Bundle?) {
        binding.tvLogin.setOnClickListener {
            if (savedInstanceState == null) {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.appContainer, LoginFragment())
                    .addToBackStack(null)
                    .commit()
            }
        }
        binding.btnCreateAccount.setOnClickListener {
            getuserInput()
        }
    }

    private fun getuserInput() {
        var nama = ""
        var email = ""
        var password = ""
        with(binding){
            nama = edName.text.toString()
            email = edEmail.text.toString()
            password = edPassword.text.toString()
        }

        val dataUser = CreateUserResponse(name = nama, email = email, password = password)

        viewModel.postCreateAccount(dataUser)



    }

}