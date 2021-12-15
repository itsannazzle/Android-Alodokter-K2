package com.nextint.alodokterbykelompok2.ui.homepage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.nextint.alodokterbykelompok2.databinding.FragmentHomeBinding
import com.nextint.alodokterbykelompok2.ui.article.ArticleAdapter
import com.nextint.alodokterbykelompok2.ui.article.ArticleViewModel
import com.nextint.alodokterbykelompok2.ui.doctor.DoctorAdapter
import com.nextint.alodokterbykelompok2.ui.doctor.DoctorViewModel
import com.nextint.alodokterbykelompok2.viewmodel.ViewModelFactory

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null){
            val factory = ViewModelFactory.getInstance(requireActivity())

            showProgressBarDoctor(true)
            val vmDoctor = ViewModelProvider(this, factory)[DoctorViewModel::class.java]
            val doctorAdapter = DoctorAdapter()
            vmDoctor.getDoctor().observe(viewLifecycleOwner, { doctors ->
                showProgressBarDoctor(false)
                doctorAdapter.setDoctor(doctors)
                //here call setOnItemClickCallback
                doctorAdapter.notifyDataSetChanged()
            })
            with(binding.rvTopDoctors){
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                this.adapter = doctorAdapter
            }

            showProgressBar(true)
            val vmArticle = ViewModelProvider(this, factory)[ArticleViewModel::class.java]
            val articleAdapter = ArticleAdapter()
            vmArticle.getArticle().observe(viewLifecycleOwner, { articles ->
                showProgressBar(false)
                articleAdapter.setArticle(articles)
                //here call setOnItemClickCallback
                articleAdapter.notifyDataSetChanged()
            })
            with(binding.rvTopArticles){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                this.adapter = articleAdapter
            }
        }
    }

    private fun showProgressBar(state: Boolean){
        binding.pbTopArticle.isVisible = state
        binding.rvTopArticles.isInvisible = state
    }

    private fun showProgressBarDoctor(state: Boolean){
        binding.pbDoctor.isVisible = state
        binding.rvTopDoctors.isInvisible = state
    }
}