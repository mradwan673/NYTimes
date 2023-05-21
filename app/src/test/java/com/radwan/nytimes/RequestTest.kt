package com.radwan.nytimes

import com.radwan.nytimes.data.remote.ApiService
import com.radwan.nytimes.utils.BASE_URL
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RequestTest {

    @Test
    fun testGetMostPopularArticles() {

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiClient = retrofit.create(ApiService::class.java)


        runBlocking {
            val articles = apiClient.getMostPopularArticles(7)
            // Assert that the `articles` list contains the expected articles.
            assertEquals(2, articles.results.size)
            assertEquals("Article 1", articles.results[0].title)
            assertEquals("Article 2", articles.results[1].title)
        }

    }

}