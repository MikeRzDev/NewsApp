package com.mikerzdev.newsapp.domain.repository

import com.mikerzdev.newsapp.domain.model.Feed

interface FeedRepository {
    suspend fun getFeed(): Feed
}