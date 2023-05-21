package com.radwan.nytimes.repository

import com.radwan.nytimes.data.model.Article
import com.radwan.nytimes.data.model.ArticleResponse
import com.radwan.nytimes.data.model.NetworkResult
import com.radwan.nytimes.data.remote.ApiService
import kotlinx.coroutines.flow.first
import org.junit.Assert.*

import org.junit.Test


class MainRepositoryTest {

    @Test
    suspend fun `when getArticlesResponseFlow is called, should return a list of articles`() {
        val apiService = object : ApiService {
            override suspend fun getMostPopularArticles(
                period: Int,
                apiKey: String,
            ): ArticleResponse {
                return ArticleResponse("",2, listOf(),"")
            }
        }

        val repository = MainRepository(apiService)
        val articleResponseFlow = repository.getArticlesResponseFlow()
        val articleResponse = articleResponseFlow.first()
        assertEquals(articleResponse,NetworkResult.Success(listOf(Article(1, 0,"Title", "Description"))))
    }

//    @Test
//    fun `when getArticlesResponseFlow is called and there is an error, should return an error`() {
//        // Create a stub of the api service
//        val apiService = object : ApiService {
//            override fun getMostPopularArticles(7): List<Article> {
//                throw RuntimeException("Error")
//            }
//        }
//
//        // Create a repository that uses the stub
//        val repository = MainRepository(apiService)
//
//        // Call the getArticlesResponseFlow method
//        val articleResponseFlow = repository.getArticlesResponseFlow()
//
//        // Verify that the repository returned an error
//        val articleResponse = articleResponseFlow.first()
//        assertThat(articleResponse).isEqualTo(NetworkResult.Failure("Error"))
//    }


}