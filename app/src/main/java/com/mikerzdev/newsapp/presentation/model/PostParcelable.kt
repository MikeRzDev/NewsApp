package com.mikerzdev.newsapp.presentation.model

import android.os.Parcelable
import com.mikerzdev.newsapp.domain.model.Post
import kotlinx.parcelize.Parcelize

@Parcelize
data class PostParcelable(
    val id: Int?,
    val title: String?,
    val content: String?,
    val excerpt: String?,
    val date: String?
) : Parcelable

fun PostParcelable.toPost() = Post(
    id = id,
    title = title,
    content = content,
    excerpt = excerpt,
    date = date
)

fun Post.toParcelable() = PostParcelable(
    id = id,
    title = title,
    content = content,
    excerpt = excerpt,
    date = date
)
