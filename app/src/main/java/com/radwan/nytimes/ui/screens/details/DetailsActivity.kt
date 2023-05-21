package com.radwan.nytimes.ui.screens.details

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.radwan.nytimes.data.model.Article
import com.google.android.material.snackbar.Snackbar
import com.radwan.nytimes.R
import com.radwan.nytimes.databinding.ActivityDetailsBinding
import com.radwan.nytimes.utils.ARTICLE

class DetailsActivity : AppCompatActivity() {

    private var article: Article? = null
    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(ARTICLE, Article::class.java)?.let {
                article = it
            } ?: "Unknown Article".showError()
        } else {
            intent.getParcelableExtra<Article>(ARTICLE)?.let {
                article = it
            } ?: "Unknown Article".showError()
        }


        updateUI(article)

    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }


    private fun String.showError() {
        Snackbar.make(binding.detailsFragmentView, this, Snackbar.LENGTH_INDEFINITE)
            .setAction("DISMISS") {}.show()
    }


    //This function updates the UI with the information from the article.

    private fun updateUI(article: Article?) {

        article?.let { item ->
            binding.tvTitle.text = item.title
            binding.tvAbstract.text = item.summary
            binding.tvAuthor.text = item.byline
            getString(R.string.publish_date_label).plus(item.published_date)
                .also { binding.tvDate.text = it }


            if (item.media != null) {
                if (item.media!!.isNotEmpty()) {

                    val meta = item.media!![0]
                    binding.tvCaption.text = meta.caption
                    getString(R.string.copyright_label).plus(meta.copyright)
                        .also { binding.tvCopyRight.text = it }

                    if (meta.mediaMetadata != null) {
                        if (meta.mediaMetadata!!.isNotEmpty()) {
                            val metaData = meta.mediaMetadata!![2]
                            Glide.with(this).load(metaData.url).into(binding.imageView)

                        }
                    }
                }
            }
        }
    }


}