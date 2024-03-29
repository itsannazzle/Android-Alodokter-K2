package com.nextint.alodokterbykelompok2.ui.login

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.nextint.alodokterbykelompok2.R
import com.nextint.alodokterbykelompok2.databinding.FragmentLoginBinding
import com.nextint.alodokterbykelompok2.model.LoginRequest
import com.nextint.alodokterbykelompok2.ui.createaccount.CreateAccountFragment
import com.nextint.alodokterbykelompok2.ui.forgotpassword.EmailRecoveryFragment
import com.nextint.alodokterbykelompok2.ui.homepage.HomePageActivity
import com.nextint.alodokterbykelompok2.utils.ReactiveField
import com.nextint.alodokterbykelompok2.utils.Result
import com.nextint.alodokterbykelompok2.utils.SessionManager
import com.nextint.alodokterbykelompok2.utils.SessionRepository
import io.reactivex.Observable


class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    lateinit var sessionRepository: SessionRepository
    private val viewModel: LoginViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        val sesi = SessionManager(requireContext())
        sessionRepository = SessionRepository.getInstance(sesi)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener(savedInstanceState)
        setupRxStream()
        //setupLoading()
    }

    private fun saveSession(username : String, token : String, exp : String) {
        sessionRepository.loginUser(username, token, exp)
        moveToHomeActivity()
    }

    private fun moveToHomeActivity() {
        startActivity(Intent(activity, HomePageActivity::class.java))
        (activity?.finish())
    }

    private fun initListener(savedInstanceState: Bundle?) {
        binding.btnLogin.setOnClickListener {
            userInput()

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
            startActivity(Intent(requireContext(), HomePageActivity::class.java))
        }

        binding.tvForgotPassword.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.appContainer, EmailRecoveryFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    @SuppressLint("CheckResult")
    private fun setupRxStream() {
        val email = with(binding.edEmail) {
            let {
                ReactiveField.emailStream(it)
            }
        }

        email.subscribe {
            binding.tilEmail.let { layout ->
                ReactiveField.helperText(it, layout, R.string.helperEmail, requireContext())
            }
        }

        val password = with(binding.edPassword) {
            let {
                ReactiveField.blankFieldStream(it)
            }
        }

        password.subscribe {
            binding.tilPassword.let { layout ->
                ReactiveField.helperText(it, layout, R.string.helperNotBlank, requireContext())
            }
        }

        val btnLoginStream = Observable.combineLatest(
            email, password, { t1: Boolean, t2: Boolean ->
                !t1 && !t2
            }
        )

        btnLoginStream.subscribe {
            binding.btnLogin.let { btn ->
                ReactiveField.buttonState(!it, btn, requireContext())
            }
        }
    }

    private fun setupLoading() {
        viewModel.loading.observe(viewLifecycleOwner, { state ->
            binding.progressBarLogin.visibility.let {
                if (state) View.VISIBLE else View.GONE
            }
        })


    }

    private fun userInput() {
        val email = binding.edEmail.text.toString()
        val password = binding.edPassword.text.toString()
        val loginReq = LoginRequest(email, password)
        binding.progressBarLogin.visibility = View.VISIBLE
        viewModel.postLogin(loginReq)
        viewModel.dataResponse.observe(viewLifecycleOwner, { result ->
            when (result) {
                is Result.Success -> {
                    binding.progressBarLogin.visibility = View.GONE
                    with(result.data){
                        saveSession(username, token, exp)
                    }

//                    val intent = Intent(requireContext(), HomePageActivity::class.java)
//                    intent.putExtra("EXTRA_USERNAME", result.data.username)
                    Log.d("Anna",result.data.username)
                    //startActivity(intent)
                }
                is Result.Error -> {
                    binding.progressBarLogin.visibility = View.GONE
                    Snackbar.make(
                        requireView(),
                        "Login gagal. (${result.throwable.message})",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }

}