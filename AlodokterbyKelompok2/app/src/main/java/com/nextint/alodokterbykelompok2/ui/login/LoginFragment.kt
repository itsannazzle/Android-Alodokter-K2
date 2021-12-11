package com.nextint.alodokterbykelompok2.ui.login

import android.annotation.SuppressLint
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
import com.nextint.alodokterbykelompok2.utils.ReactiveField
import io.reactivex.Observable
import java.util.*


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
        setupRxStream()
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

    @SuppressLint("CheckResult")
    private fun setupRxStream(){
        val email = with(binding.edEmail){
            let {
                ReactiveField.emailStream(it)
            }
        }

        email.subscribe {
            binding.tilEmail.let { layout ->
                ReactiveField.helperText(it,layout,R.string.helperEmail,requireContext())
            }
        }

        val password = with(binding.edPassword){
            let {
                ReactiveField.blankFieldStream(it)
            }
        }

        password.subscribe {
            binding.tilPassword.let { layout ->
                ReactiveField.helperText(it,layout,R.string.helperNotBlank,requireContext())
            }
        }

        val btnLoginStream = Observable.combineLatest(
            email, password, {
                t1 : Boolean, t2 : Boolean -> !t1 && !t2
            }
        )

        btnLoginStream.subscribe {
            binding.btnLogin.let { btn ->
                ReactiveField.buttonState(!it,btn,requireContext())
            }
        }
    }

}