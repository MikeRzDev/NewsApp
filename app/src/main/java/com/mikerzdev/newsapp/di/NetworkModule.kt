package com.mikerzdev.newsapp.di

import android.content.Context
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.mikerzdev.newsapp.core.MegaBytes
import com.mikerzdev.newsapp.data.repository.FeedRepositoryImpl
import com.mikerzdev.newsapp.data.source.remote.FeedApi
import com.mikerzdev.newsapp.data.source.remote.config.BASE_URL
import com.mikerzdev.newsapp.domain.repository.FeedRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Cache
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(
        @ApplicationContext context: Context
    ): Retrofit {
        val cacheSize = 5.MegaBytes
        val cache = Cache(context.cacheDir, cacheSize)
        val contentType = "application/json".toMediaType()
        val json = Json { ignoreUnknownKeys = true }
        val kotlinSerializationConverterFactory = json.asConverterFactory(contentType)
        val okHttpClient = OkHttpClient.Builder()
            .cache(cache)
            .connectTimeout(45, TimeUnit.SECONDS)
            .writeTimeout(45, TimeUnit.SECONDS)
            .readTimeout(45, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(kotlinSerializationConverterFactory)
            .build()
    }


    @Provides
    @Singleton
    fun provideFeedApi(retrofit: Retrofit): FeedApi {
        return retrofit.create(FeedApi::class.java)
    }

    @Provides
    @Singleton
    fun provideFeedRepository(feedApi: FeedApi): FeedRepository {
        return FeedRepositoryImpl(feedApi)
    }

}
