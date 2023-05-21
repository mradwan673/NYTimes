package com.radwan.nytimes.data.remote

import com.radwan.nytimes.data.model.ArticleResponse
import com.radwan.nytimes.utils.API_KEY
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("mostpopular/v2/viewed/{period}.json")
    suspend fun getMostPopularArticles(
        @Path("period") period: Int,
        @Query("api-key") apiKey: String =  API_KEY
    ) : ArticleResponse

}