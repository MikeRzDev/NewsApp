package com.mikerzdev.newsapp.data.repository

import com.mikerzdev.newsapp.data.mapper.toDomain
import com.mikerzdev.newsapp.data.source.remote.FeedApi
import com.mikerzdev.newsapp.domain.repository.FeedRepository

class FeedRepositoryImpl(private val feedApi: FeedApi) : FeedRepository {
    override suspend fun getFeed() = feedApi.getFeed().toDomain()
}