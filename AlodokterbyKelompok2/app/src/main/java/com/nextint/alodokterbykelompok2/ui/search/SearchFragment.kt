package com.nextint.alodokterbykelompok2.ui.search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nextint.alodokterbykelompok2.R
import com.nextint.alodokterbykelompok2.databinding.FragmentSearchBinding
import com.nextint.alodokterbykelompok2.ui.article.ArticleAdapter
import com.nextint.alodokterbykelompok2.ui.article.ArticleViewModel
import com.nextint.alodokterbykelompok2.viewmodel.ViewModelFactory

class SearchFragment : Fragment(), ArticleAdapter.OnItemClickCallback {
    private lateinit var binding: FragmentSearchBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.toolbarSearch.setupWithNavController(navController, appBarConfiguration)
        binding.ivAset.isVisible = true
        binding.tvHasilPencarian.isInvisible = true
        showProgressBar(false)

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query.isNullOrEmpty()) return true
                else searchArticle(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

    }

    private fun searchArticle(query: String) {
        binding.ivAset.isInvisible = true
        showProgressBar(true)
        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(this, factory)[ArticleViewModel::class.java]
        val articleAdapter = ArticleAdapter()
        binding.tvHasilPencarian.isVisible = true
        binding.tvHasilPencarian.text = getString(R.string.string_hasil_pencarian, query)
        viewModel.setParamSearch(query)
        viewModel.getSearchArticle().observe(viewLifecycleOwner, { articles ->
            showProgressBar(false)
            articleAdapter.setArticle(articles)
            articleAdapter.setOnItemClickCallback(this)
            articleAdapter.notifyDataSetChanged()
        })
        with(binding.rvSearch) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            this.adapter = articleAdapter
        }
    }

    private fun showProgressBar(state: Boolean) {
        binding.pbSearch.isVisible = state
        binding.rvSearch.isInvisible = state
    }

    override fun onItemClicked(id: String) {
        val bundle = bundleOf("id" to id)
        view?.findNavController()?.navigate(R.id.nav_detail_article, bundle)
    }
}