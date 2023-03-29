package com.mikerzdev.newsapp.domain.model

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


data class Post(
    val id: Int?,
    val title: String?,
    val content: String?,
    val excerpt: String?,
    val date: String?
) {
    val dateTime: LocalDate
        get() {
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            return LocalDateTime.parse(date, formatter).toLocalDate()
        }
}