package com.nextint.alodokterbykelompok2.ui.createaccount

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.nextint.alodokterbykelompok2.R
import com.nextint.alodokterbykelompok2.databinding.FragmentCompleteProfileBinding
import com.nextint.alodokterbykelompok2.databinding.FragmentCreateAccountBinding
import com.nextint.alodokterbykelompok2.model.CreateUserResponse
import com.nextint.alodokterbykelompok2.ui.login.LoginFragment

class CompleteProfileFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentCompleteProfileBinding
    private val viewModel: CreateAccountViewModel by viewModels()

    private var name:String = ""
    private var birthday:String = ""
    private var gender:String = ""
    private var phone:String = ""
    private var img:String = ""
    private var username:String = ""
    private var email:String = ""
    private var password:String  = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCompleteProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
    }

    private fun initListener() {
        binding.btnContinue.setOnClickListener(this)
    }

    private fun register() {
        name = ""
        birthday = binding.edBirthday.text.toString()
        gender = ""
        phone = binding.edPhone.text.toString()
        username = binding.edUsername.text.toString()
        email = ""
        password = ""

        if (!name.isEmpty() && !birthday.isEmpty() && !gender.isEmpty() && !phone.isEmpty() && !username.isEmpty() && !email.isEmpty() && !password.isEmpty() ){

//            viewModel.postCreateAccount(dataUser)
        } else{

        }
    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }

}