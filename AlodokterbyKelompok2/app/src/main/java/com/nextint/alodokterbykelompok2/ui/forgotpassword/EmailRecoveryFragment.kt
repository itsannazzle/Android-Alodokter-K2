package com.nextint.alodokterbykelompok2.ui.forgotpassword

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.nextint.alodokterbykelompok2.R
import com.nextint.alodokterbykelompok2.databinding.FragmentEmailRecoveryBinding
import com.nextint.alodokterbykelompok2.ui.createaccount.CreateAccountFragment
import com.nextint.alodokterbykelompok2.utils.ReactiveField
import com.nextint.alodokterbykelompok2.utils.Result
import io.reactivex.Observable


class EmailRecoveryFragment : Fragment() {
    private lateinit var  binding : FragmentEmailRecoveryBinding
    private val viewModel : LupaPasswordViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEmailRecoveryBinding.inflate(inflater,container,false)
        return binding.root
    }

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()

        val emailStream = binding.edEmail.let {
            ReactiveField.emailStream(it)
        }

        emailStream.subscribe {
            ReactiveField.helperText(it,binding.tilEmail,R.string.helperEmail,this.requireContext())
            ReactiveField.buttonState(it,binding.btnLanjutkan,this.requireContext())
        }

    }

    private fun postUserEmail(email : String){
        viewModel.sendToken(email)

    }

    private fun initListener(){
        binding.btnLanjutkan.setOnClickListener {
            postUserEmail(binding.edEmail.text.toString())
            viewModel.sendEmailResp.observe(viewLifecycleOwner,{ result ->
                when(result){
                    is Result.Success ->{
                        parentFragmentManager.beginTransaction()
                            .replace(R.id.appContainer, TokenValidationFragment())
                            .addToBackStack(null)
                            .commit()
                    }
                    is Result.Error -> Snackbar.make(requireView(),"Gagal ${result.throwable.message}",Snackbar.LENGTH_SHORT).show()
                    else -> Snackbar.make(requireView(),"Masukan email yang terdaftar",Snackbar.LENGTH_SHORT).show()
                }
            })
        }
    }

}