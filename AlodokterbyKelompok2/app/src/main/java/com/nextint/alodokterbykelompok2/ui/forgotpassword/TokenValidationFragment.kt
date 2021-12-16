package com.nextint.alodokterbykelompok2.ui.forgotpassword

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.jakewharton.rxbinding2.widget.RxTextView
import com.nextint.alodokterbykelompok2.R
import com.nextint.alodokterbykelompok2.databinding.FragmentTokenValidationBinding
import com.nextint.alodokterbykelompok2.model.NewPasswordRequest
import com.nextint.alodokterbykelompok2.ui.createaccount.CreateAccountFragment
import com.nextint.alodokterbykelompok2.ui.login.LoginFragment
import com.nextint.alodokterbykelompok2.utils.ReactiveField
import com.nextint.alodokterbykelompok2.utils.Result
import io.reactivex.Observable


class TokenValidationFragment : Fragment() {
    private lateinit var binding : FragmentTokenValidationBinding
    private val viewModel : LupaPasswordViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTokenValidationBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inputStream()
    }

    @SuppressLint("CheckResult")
    private fun inputStream(){
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
        val kodeStream = binding.edKode.let {
            ReactiveField.blankFieldStream(it)
        }

        kodeStream.subscribe {
            ReactiveField.helperText(it,binding.tilKode,R.string.helperNotBlank,this.requireContext())
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
            email,kodeStream, passStream, passConfrimStream, {
                t1 : Boolean, t2 : Boolean, t3 : Boolean, t4 : Boolean -> !t1 && !t2 &&!t3 && !t4
            }
        )

        btnStream.subscribe {
            ReactiveField.buttonState(!it,binding.btnLanjutkan,requireContext())
        }
    }

    private fun postNewPassword(){
        val token = binding.edKode.text.toString()
        val email = binding.edEmail.text.toString()
        val password = binding.edPassword.text.toString()
        val req = NewPasswordRequest(password, email, token)
        viewModel.newPassword(req)

    }

    private fun initListener(){
        binding.btnLanjutkan.setOnClickListener {
            postNewPassword()

            viewModel.newPasswordResp.observe(viewLifecycleOwner,{
                result ->
                when(result){
                    is Result.Success -> {
                        parentFragmentManager.beginTransaction()
                            .replace(R.id.appContainer, LoginFragment())
                            .addToBackStack(null)
                            .commit()
                        Snackbar.make(requireView(),"Password berhasil diubah",Snackbar.LENGTH_SHORT).show()
                    }
                    is Result.Error -> Snackbar.make(requireView(),"Gagal ubah passwod ${result.throwable.message}",Snackbar.LENGTH_SHORT).show()
                    else -> Snackbar.make(requireView(),"EROR",Snackbar.LENGTH_SHORT).show()
                }
            })
        }
    }

}