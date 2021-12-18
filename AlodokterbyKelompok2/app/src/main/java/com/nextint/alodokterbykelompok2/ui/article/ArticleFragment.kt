package com.nextint.alodokterbykelompok2.ui.article

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
import com.nextint.alodokterbykelompok2.databinding.FragmentArticleBinding
import com.nextint.alodokterbykelompok2.viewmodel.ViewModelFactory

class ArticleFragment : Fragment(R.layout.fragment_article),  ArticleAdapter.OnItemClickCallback {
    private lateinit var binding: FragmentArticleBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentArticleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = ViewModelFactory.getInstance(requireActivity())
        val vmArticle = ViewModelProvider(this, factory)[ArticleViewModel::class.java]
        val articleAdapter = ArticleAdapter()
        vmArticle.getArticle().observe(viewLifecycleOwner, { articles ->
            showProgressBar(false)
            articleAdapter.setArticle(articles)
            articleAdapter.setOnItemClickCallback(this)
            articleAdapter.notifyDataSetChanged()
        })
        with(binding.rvArticles){
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            this.adapter = articleAdapter
        }
    }

    override fun onItemClicked(id: String) {
        Log.d("HOME", "Tes Klik id: $id")
        val bundle = bundleOf("id" to id)
        view?.findNavController()?.navigate(R.id.nav_detail_article, bundle)
    }

    private fun showProgressBar(state: Boolean){
        binding.rvArticles.isInvisible = state
    }
}