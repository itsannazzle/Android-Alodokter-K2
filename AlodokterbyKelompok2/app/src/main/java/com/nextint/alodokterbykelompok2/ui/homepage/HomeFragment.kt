package com.nextint.alodokterbykelompok2.ui.homepage

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nextint.alodokterbykelompok2.R
import com.nextint.alodokterbykelompok2.databinding.FragmentHomeBinding
import com.nextint.alodokterbykelompok2.ui.article.ArticleAdapter
import com.nextint.alodokterbykelompok2.ui.article.ArticleViewModel
import com.nextint.alodokterbykelompok2.ui.doctor.DoctorAdapter
import com.nextint.alodokterbykelompok2.ui.doctor.DoctorViewModel
import com.nextint.alodokterbykelompok2.utils.SessionManager
import com.nextint.alodokterbykelompok2.utils.SessionRepository
import com.nextint.alodokterbykelompok2.viewmodel.ViewModelFactory

class HomeFragment : Fragment(), ArticleAdapter.OnItemClickCallback {
    private lateinit var binding: FragmentHomeBinding
    lateinit var sessionRepository: SessionRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val sesi = SessionManager(requireContext())
        sessionRepository = SessionRepository.getInstance(sesi)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvWelcomeUser.text = getString(R.string.selamatdatang, sessionRepository.getUsername())
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
                articleAdapter.setOnItemClickCallback(this)
                articleAdapter.notifyDataSetChanged()
            })
            with(binding.rvTopArticles){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                this.adapter = articleAdapter
            }

            binding.tvSeeAllArticles.setOnClickListener {
                view.findNavController().navigate(R.id.nav_article)
            }
        }
    }

    override fun onItemClicked(id: String) {
        Log.d("HOME", "Tes Klik id: $id")
        val bundle = bundleOf("id" to id)
        view?.findNavController()?.navigate(R.id.nav_detail_article, bundle)
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