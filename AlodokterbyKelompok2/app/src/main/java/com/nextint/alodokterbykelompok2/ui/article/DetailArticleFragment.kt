package com.nextint.alodokterbykelompok2.ui.article

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nextint.alodokterbykelompok2.R
import com.nextint.alodokterbykelompok2.data.local.DetailArticleEntity
import com.nextint.alodokterbykelompok2.databinding.FragmentDetailArticleBinding
import com.nextint.alodokterbykelompok2.utils.DateTimeFormat
import com.nextint.alodokterbykelompok2.viewmodel.ViewModelFactory

class DetailArticleFragment : Fragment() {
    private lateinit var binding: FragmentDetailArticleBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailArticleBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.toolbarDetail.setupWithNavController(navController, appBarConfiguration)

        if (activity != null) {
            showProgressBar(true)

            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[ArticleViewModel::class.java]
            val dataId = arguments?.getString("id")
            Log.d("DETAIL", "Tes nerima id: $dataId")
            if (dataId != null) {
                viewModel.setDetail(dataId)
                viewModel.getDetail().observe(viewLifecycleOwner, { detail ->
                    populateDataDetail(detail)
                })
            }
        }
    }

    private fun populateDataDetail(detail: DetailArticleEntity) {
        with(binding) {
            tvTitle.text = detail.title
            tvReference.text = detail.reference
            tvDatePublished.text = detail.datePosted?.let { DateTimeFormat.formatDate(it) }
            tvDesc.text = detail.description
            Glide.with(this@DetailArticleFragment)
                .load(detail.image)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.image_article)
                        .error(R.drawable.no_article_image)
                ).into(binding.ivPictureArticle)
        }
        showProgressBar(false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_detail_article, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun showProgressBar(state: Boolean){
        binding.pbDetailArticle.isVisible = state
    }

}