package com.radwan.nytimes.repository

import com.radwan.nytimes.data.model.NetworkResult
import com.radwan.nytimes.data.remote.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

open class MainRepository @Inject constructor(private val apiService: ApiService) {

   // This function gets the most popular articles from the API, and return A flow of articles.

    open suspend fun getArticlesResponseFlow()  = flow {
        emit(NetworkResult.Loading(true))
        val response = apiService.getMostPopularArticles(7)
       emit(NetworkResult.Success(response.results))
    }.flowOn(Dispatchers.IO)
     .catch { e ->
        emit(NetworkResult.Failure(e.message ?: "Unknown Error"))
    }

}