package com.mikerzdev.newsapp.presentation.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    val appError =  MutableLiveData<Throwable>()
    val showProgress = MutableLiveData<Boolean>()
}