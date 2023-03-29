package com.mikerzdev.newsapp.domain.usecase

import com.mikerzdev.newsapp.domain.model.Feed
import com.mikerzdev.newsapp.domain.repository.FeedRepository
import com.mikerzdev.newsapp.domain.usecase.base.UseCase
import javax.inject.Inject

class GetFeedUseCase @Inject constructor(
    private val repository: FeedRepository
) : UseCase<Feed, UseCase.None>() {

    override suspend fun run(params: None): Feed {
        return repository.getFeed()
    }

}