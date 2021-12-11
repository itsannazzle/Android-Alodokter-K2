package com.nextint.alodokterbykelompok2.ui.createaccount

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.jakewharton.rxbinding2.widget.RxTextView
import com.nextint.alodokterbykelompok2.R
import com.nextint.alodokterbykelompok2.databinding.FragmentCreateAccountBinding
import com.nextint.alodokterbykelompok2.model.CreateUserResponse
import com.nextint.alodokterbykelompok2.ui.login.LoginFragment
import com.nextint.alodokterbykelompok2.utils.ReactiveField
import io.reactivex.Observable


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
        setupRxStream()
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

        with(binding){
            val nama = edName.text.toString()
            val email = edEmail.text.toString()
            val password = edPassword.text.toString()
            val dataUser = CreateUserResponse(name = nama, email = email, password = password)
            viewModel.postCreateAccount(dataUser)
        }


    }

    @SuppressLint("CheckResult")
    private fun setupRxStream(){
        val nama = with(binding.edName){
            let {
                ReactiveField.blankFieldStream(it)
            }
        }
        nama.subscribe {
            binding.tilName.let { layout ->
                ReactiveField.helperText(it,layout,R.string.helperNotBlank,requireContext())
            }
        }

        val email = with(binding.edEmail){
            let { ReactiveField.emailStream(it) }
        }
        email.subscribe {
            binding.tilEmail.let { layout ->
                ReactiveField.helperText(it,layout,R.string.helperEmail,requireContext())
            }
        }
        val passStream = binding.edPassword.let {
            ReactiveField.minLengthStream(it, 8)
        }
        passStream?.subscribe {
            binding.tilPassword.let { it1 ->
                ReactiveField.helperText(
                    it,
                    it1, R.string.password_not_valid, this.requireContext()
                )
            }
        }

        val passConfrimStream = Observable.merge(
            binding.edPassword.let {
                RxTextView.textChanges(it)
                    .map { pass ->
                        pass.toString() != binding.edRetypePassword.text.toString()
                    }
            },
            binding.edRetypePassword.let {
                RxTextView.textChanges(it)
                    .map { confPass -> confPass.toString() != binding.edPassword.text.toString() }
            }
        )

        passConfrimStream.subscribe {
            binding.tilRetypePassword.let { it1 ->
                ReactiveField.helperText(
                    it,
                    it1, R.string.password_not_same, this.requireContext()
                )
            }
        }

        val btnStream = Observable.combineLatest(
            nama,
            email,
            passStream,
            passConfrimStream, {
                t1 : Boolean, t2 : Boolean, t3 : Boolean, t4 : Boolean -> !t1 && !t2 && !t3 && !t4
            }
        )

        btnStream.subscribe {
            binding.btnCreateAccount.let { btn ->
                ReactiveField.buttonState(!it,btn,requireContext())
            }
        }

    }

}