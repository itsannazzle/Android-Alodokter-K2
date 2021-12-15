package com.nextint.alodokterbykelompok2.ui.article

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nextint.alodokterbykelompok2.R
import com.nextint.alodokterbykelompok2.data.local.ArticleEntity
import com.nextint.alodokterbykelompok2.databinding.ItemRvArticleBinding
import com.nextint.alodokterbykelompok2.data.remote.response.article.Article
import com.nextint.alodokterbykelompok2.utils.DateTimeFormat

class ArticleAdapter : RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {
    private var listArticle = ArrayList<ArticleEntity>()

    fun setArticle(articles: List<ArticleEntity>?) {
        if (articles.isNullOrEmpty()) return
        this.listArticle.clear()
        this.listArticle.addAll(articles)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val itemArticleBinding = ItemRvArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(itemArticleBinding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = listArticle[position]
        holder.bind(article)
    }

    override fun getItemCount(): Int = listArticle.size

    class ArticleViewHolder(private val binding: ItemRvArticleBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(article: ArticleEntity){
            with(binding){
                tvReference.text = article.reference
                tvDatePublished.text = article.datePosted?.let { DateTimeFormat.formatDate(it) }
                tvTitle.text = article.title
                tvDesc.text = article.description
                Glide.with(itemView.context)
                    .load(article.image)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.image_article)
                            .error(R.drawable.no_article_image)
                    ).into(ivPictureArticle)
            }
        }
    }

}