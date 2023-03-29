package com.mikerzdev.newsapp.data.model.response
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class FeedResponse(
    @SerialName("posts")
    val posts: List<PostResponse>? = null
)

