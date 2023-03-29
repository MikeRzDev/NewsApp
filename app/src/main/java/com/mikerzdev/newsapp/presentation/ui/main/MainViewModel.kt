package com.mikerzdev.newsapp.presentation.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mikerzdev.newsapp.domain.model.Feed
import com.mikerzdev.newsapp.domain.usecase.GetFeedUseCase
import com.mikerzdev.newsapp.domain.usecase.base.UseCase
import com.mikerzdev.newsapp.presentation.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(private val getFeedUseCase: GetFeedUseCase) :
    BaseViewModel() {

    val feedLiveData = MutableLiveData<Feed>()

    fun getFeed() = getFeedUseCase(
        params = UseCase.None,
        scope = viewModelScope,
        onSuccess = {
            feedLiveData.value = it
        },
        onError = {
            appError.value = it
        },
        onWait = {
            showProgress.value = it
        }
    )

}