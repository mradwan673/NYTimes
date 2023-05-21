package com.radwan.nytimes.ui.screens.master

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.google.android.material.snackbar.Snackbar
import com.radwan.nytimes.R
import com.radwan.nytimes.ui.adapter.ClickInterface
import com.radwan.nytimes.ui.adapter.ArticleAdapter
import com.radwan.nytimes.data.model.NetworkResult
import com.radwan.nytimes.databinding.ActivityMainBinding
import com.radwan.nytimes.ui.screens.details.DetailsActivity
import com.radwan.nytimes.data.model.Article
import com.radwan.nytimes.utils.ARTICLE
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val mainViewModel: MainViewModel by viewModels()
    @Inject
    lateinit var articleAdapter: ArticleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        subscribeUi()

    }


    //This function initializes the activity.
    //It sets the title of the activity, sets the adapter for the recycler view, and sets the click listener for the retry button.

    private fun init() {

        title = getString(R.string.popular_articles)
        binding.rvArticles.adapter = articleAdapter

        articleAdapter.setItemClick(object : ClickInterface<Article> {
            override fun onClick(data: Article) {
                val intent = Intent(this@MainActivity, DetailsActivity::class.java)
                intent.putExtra(ARTICLE, data)
                startActivity(intent)
            }
        })

        binding.btnRetry.setOnClickListener {
            mainViewModel.getArticlesFlow()
        }

    }

    //This function subscribes to the view model's article response flow and updates the UI accordingly.
    //When the flow is loading, the progress bar is shown and the list view is hidden.
    //When the flow fails, an error message is shown and the list view is shown.
    //When the flow Success, the list view is updated with the articles.

    private fun subscribeUi() {
        mainViewModel.articleResponse.observe(this) {
            when(it) {
                is NetworkResult.Loading -> {
                    binding.progressbar.isVisible = it.isLoading
                    binding.linearLayoutView.isVisible = false
                }

                is NetworkResult.Failure -> {
                    binding.errorMsg.text = it.errorMessage
                    binding.progressbar.isVisible = false
                    binding.linearLayoutView.isVisible = true
                }

                is  NetworkResult.Success -> {
                    articleAdapter.updateArticles(it.data)
                    binding.progressbar.isVisible = false
                    binding.linearLayoutView.isVisible = false

                    if (it.data.isEmpty()) {
                        Snackbar.make(binding.root, "No articles found.", Snackbar.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }



}
