package com.mikerzdev.newsapp.data.mapper

import com.mikerzdev.newsapp.data.model.response.FeedResponse
import com.mikerzdev.newsapp.data.model.response.PostResponse
import com.mikerzdev.newsapp.domain.model.Feed
import com.mikerzdev.newsapp.domain.model.Post

fun FeedResponse.toDomain() = Feed(
    posts = posts?.map { it.toDomain() }
)

fun PostResponse.toDomain() = Post(
    id = id,
    title = title,
    content = content,
    excerpt = excerpt,
    date = date
)


