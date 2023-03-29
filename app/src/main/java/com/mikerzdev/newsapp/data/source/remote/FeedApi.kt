package com.mikerzdev.newsapp.data.source.remote

import com.mikerzdev.newsapp.data.model.response.FeedResponse
import retrofit2.http.GET

interface FeedApi {
    @GET("api/v1/news")
    suspend fun getFeed(): FeedResponse
}