package com.nextint.alodokterbykelompok2.ui.homepage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.nextint.alodokterbykelompok2.databinding.FragmentHomeBinding
import com.nextint.alodokterbykelompok2.ui.article.ArticleAdapter
import com.nextint.alodokterbykelompok2.ui.article.ArticleViewModel
import com.nextint.alodokterbykelompok2.ui.doctor.DoctorAdapter
import com.nextint.alodokterbykelompok2.ui.doctor.DoctorViewModel

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
            val vmDoctor = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[DoctorViewModel::class.java]
            val doctors = vmDoctor.getDoctor()

            val doctorAdapter = DoctorAdapter()
            doctorAdapter.setDoctor(doctors)

            with(binding.rvTopDoctors){
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = doctorAdapter
            }

            val vmArticle = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[ArticleViewModel::class.java]
            val articles = vmArticle.getArticle()

            val articleAdapter = ArticleAdapter()
            articleAdapter.setArticle(articles)

            with(binding.rvTopArticles){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = articleAdapter
            }
        }
    }
}