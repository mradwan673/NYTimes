package com.radwan.nytimes.ui.screens.master

import com.radwan.nytimes.data.model.Article
import com.radwan.nytimes.data.model.ArticleResponse
import com.radwan.nytimes.data.model.NetworkResult
import com.radwan.nytimes.data.remote.ApiService
import com.radwan.nytimes.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert.*

import org.junit.Test

class MainViewModelTest {

    @Test
    fun `when getArticlesFlow is called, should return a list of articles`() {
        // Create a mock of the repository
        val apiService = object : ApiService {

            override suspend fun getMostPopularArticles(
                period: Int,
                apiKey: String,
            ): ArticleResponse {
                return ArticleResponse("",2, listOf(),"")
            }

        }


        val repository = object : MainRepository(apiService = apiService) {
           override suspend fun getArticlesResponseFlow(): Flow<NetworkResult<List<Article>>> {
                return flowOf(NetworkResult.Success(listOf(Article(1,0, "Title", "Description"))))
            }
        }


        val viewModel = MainViewModel(repository)
        viewModel.getArticlesFlow()

        val articleResponse = viewModel.articleResponse
        assertEquals(articleResponse.value,NetworkResult.Success(listOf(Article(1,0, "Title", "Description"))))
    }

}