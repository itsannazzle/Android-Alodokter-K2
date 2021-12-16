package com.nextint.alodokterbykelompok2.ui.createaccount

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.jakewharton.rxbinding2.widget.RxTextView
import com.nextint.alodokterbykelompok2.R
import com.nextint.alodokterbykelompok2.databinding.FragmentCreateAccountBinding
import com.nextint.alodokterbykelompok2.model.CreateUserResponse
import com.nextint.alodokterbykelompok2.ui.login.LoginFragment
import com.nextint.alodokterbykelompok2.utils.DatePicker
import com.nextint.alodokterbykelompok2.utils.ReactiveField
import com.nextint.alodokterbykelompok2.utils.Result
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
        createAccountState()
        loading()
        binding.genderRb.check(R.id.radio_perempuan)

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
        binding.btnDob.setOnClickListener { showDatePicker() }
    }

    private fun getuserInput() {
        with(binding) {
            val nama = edName.text.toString()
            val email = edEmail.text.toString()
            val password = edPassword.text.toString()
            val username = edUsername.text.toString()
            val noTelp = edNotelp.text.toString()
            val dob = tvDobValue.text.toString()
            val id = binding.genderRb.checkedRadioButtonId
            val gnr = binding.genderRb.findViewById<RadioButton>(id)
            val dataUser = CreateUserResponse(
                name = nama, username = username, email = email, password = password,
                phone = noTelp, birthday = dob, gender = gnr.text.toString()
            )
            viewModel.postCreateAccount(dataUser)
        }


    }

    @SuppressLint("CheckResult")
    private fun setupRxStream() {
        val nama = with(binding.edName) {
            let {
                ReactiveField.blankFieldStream(it)
            }
        }
        nama.subscribe {
            binding.tilName.let { layout ->
                ReactiveField.helperText(it, layout, R.string.helperNotBlank, requireContext())
            }
        }

        val username = with(binding.edUsername) {
            let {
                ReactiveField.minLengthStream(it, 8)
            }
        }

        username.subscribe {
            binding.tilUsername.let { layout ->
                ReactiveField.helperText(it, layout, R.string.minLeng8, requireContext())
            }
        }

        val nomorTelpon = with(binding.edNotelp) {
            let {
                ReactiveField.minLengthStream(it, 12)
            }
        }

        nomorTelpon.subscribe {
            binding.tilNotelp.let { layout ->
                ReactiveField.helperText(it, layout, R.string.minLeng12, requireContext())
            }
        }

        val email = with(binding.edEmail) {
            let { ReactiveField.emailStream(it) }
        }
        email.subscribe {
            binding.tilEmail.let { layout ->
                ReactiveField.helperText(it, layout, R.string.helperEmail, requireContext())
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
            username,
            nomorTelpon,
            passStream,
            passConfrimStream,
            { t1: Boolean, t2: Boolean, t3: Boolean, t4: Boolean, t5: Boolean, t6: Boolean ->
                !t1 && !t2 && !t3 && !t4 && !t5 && !t6
            }
        )

        btnStream.subscribe {
            binding.btnCreateAccount.let { btn ->
                ReactiveField.buttonState(!it, btn, requireContext())
            }
        }

    }

    private fun createAccountState() {
        viewModel.dataResponse.observe(viewLifecycleOwner, { result ->
            when (result) {
                is Result.Success -> {
                    with(binding) {
                        edName.text?.clear()
                        edEmail.text?.clear()
                        edPassword.text?.clear()
                        edUsername.text?.clear()
                        edNotelp.text?.clear()
                        edRetypePassword.text?.clear()
                        tvDobValue.text = getString(R.string.pilih_tanggal_lahir)
                    }
                    Snackbar.make(requireView(), "Akun berhasil dibuat", Snackbar.LENGTH_SHORT)
                        .show()
                }
                is Result.Error -> result.throwable.message?.let {
                    Snackbar.make(
                        requireView(),
                        it, Snackbar.LENGTH_SHORT
                    ).show()
                }
                else -> {
                    viewModel.message.observe(viewLifecycleOwner,{
                        Snackbar.make(
                            requireView(),
                            it, Snackbar.LENGTH_SHORT
                        ).show()
                    })
                }
            }
        })
    }

    private fun loading() {
        viewModel.loading.observe(viewLifecycleOwner, {
            if (it) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        })
    }


    fun showDatePicker() {
        val datePicker = DatePicker()
        val supportFragment = requireActivity().supportFragmentManager

        supportFragment.setFragmentResultListener("REQUEST_KEY", viewLifecycleOwner) { rs, bundle ->
            if (rs == "REQUEST_KEY") {
                val date = bundle.getString("SELECTED_DATE")
                binding.tvDobValue.text = date
            }
        }
        datePicker.show(supportFragment, "DatePickerFragment")
    }
}