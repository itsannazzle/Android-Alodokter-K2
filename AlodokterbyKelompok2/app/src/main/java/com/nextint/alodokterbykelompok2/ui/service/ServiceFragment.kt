package com.nextint.alodokterbykelompok2.ui.service

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nextint.alodokterbykelompok2.R
import com.nextint.alodokterbykelompok2.databinding.FragmentServiceBinding
import com.nextint.alodokterbykelompok2.ui.doctor.DoctorListAdapter
import com.nextint.alodokterbykelompok2.ui.doctor.DoctorViewModel
import com.nextint.alodokterbykelompok2.viewmodel.ViewModelFactory

class ServiceFragment : Fragment(R.layout.fragment_service){
    private lateinit var binding: FragmentServiceBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentServiceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = ViewModelFactory.getInstance(requireActivity())
        val vmDoctor = ViewModelProvider(this, factory)[DoctorViewModel::class.java]
        val doctorListAdapter = DoctorListAdapter()
        vmDoctor.getDoctor().observe(viewLifecycleOwner, { doctors ->
            showProgressBar(false)
            doctorListAdapter.setDoctor(doctors)
            doctorListAdapter.notifyDataSetChanged()
        })
        with(binding.rvdoctors) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            this.adapter = doctorListAdapter
        }
    }
    private fun showProgressBar(state: Boolean){
        binding.rvdoctors.isInvisible = state
    }
}