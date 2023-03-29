package com.mikerzdev.newsapp

import com.mikerzdev.newsapp.domain.model.Feed
import com.mikerzdev.newsapp.domain.model.Post
import com.mikerzdev.newsapp.domain.repository.FeedRepository
import com.mikerzdev.newsapp.domain.usecase.GetFeedUseCase
import com.mikerzdev.newsapp.domain.usecase.base.UseCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GetFeedUseCaseTest {
    private lateinit var useCase: GetFeedUseCase
    private lateinit var repository: FeedRepository

    @Before
    fun setup() {
        repository = mock()
        useCase = GetFeedUseCase(repository)
    }

    @Test
    fun `when run use case then should return feed from repository`() = runBlocking {
        // Given
        val feed = Feed(
            listOf(
                Post(1, "Article1", "text", "text summary", "2020-01-01 00:00:00"),
                Post(2, "Article2", "text", "text summary", "2020-01-01 00:00:00")
            )
        )
        whenever(repository.getFeed()).thenReturn(feed)

        // When
        val result = useCase.run(UseCase.None)

        // Then
        assertEquals(feed, result)
    }
}