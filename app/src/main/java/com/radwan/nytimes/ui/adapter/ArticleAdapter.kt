package com.radwan.nytimes.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.radwan.nytimes.databinding.ItemAtricleViewBinding
import com.radwan.nytimes.data.model.Article
import javax.inject.Inject


class ArticleAdapter @Inject constructor() : RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    var articles = mutableListOf<Article>()
    private var clickInterface: ClickInterface<Article>? = null

    fun updateArticles(articles: List<Article>) {
        this.articles = articles.toMutableList()
        notifyItemRangeInserted(0, articles.size)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding = ItemAtricleViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articles[position]
        holder.view.tvTitle.text = article.title
        holder.view.tvSummary.text = article.summary
        holder.view.tvDate.text = article.published_date

        if (article.media != null && article.media!!.isNotEmpty()) {
            val meta = article.media!![0]
            if (meta.mediaMetadata != null) {
                if (meta.mediaMetadata!!.isNotEmpty()) {
                    val metaData = meta.mediaMetadata!![0]
                    Glide.with(holder.view.articleImage.context).load(metaData.url)
                        .into(holder.view.articleImage)
                }
            }
        }

        holder.view.articleCard.setOnClickListener {
            clickInterface?.onClick(article)
        }
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    fun setItemClick(clickInterface: ClickInterface<Article>) {
        this.clickInterface = clickInterface
    }

    class ArticleViewHolder(val view: ItemAtricleViewBinding) : RecyclerView.ViewHolder(view.root)

}

interface ClickInterface<T> {
    fun onClick(data: T)
}

