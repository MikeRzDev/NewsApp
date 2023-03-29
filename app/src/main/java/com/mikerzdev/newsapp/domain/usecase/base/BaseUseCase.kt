package com.mikerzdev.newsapp.domain.usecase.base

import kotlinx.coroutines.*

abstract class UseCase<out Type, in Params> where Type : Any {

    abstract suspend fun run(params: Params): Type

    operator fun invoke(
        params: Params,
        scope: CoroutineScope = GlobalScope,
        onSuccess: (Type) -> Unit = {},
        onError: (exception: Throwable) -> Unit = {},
        onWait: (showProgress: Boolean) -> Unit = {}
    ) {
        scope.launch(Dispatchers.Main) {
            onWait(true)
            supervisorScope {
                val deferred = async(Dispatchers.IO) {
                    run(params)
                }
                try {
                    val result = deferred.await()
                    onWait(false)
                    onSuccess(result)
                } catch (ex: Throwable) {
                    onWait(false)
                    onError(ex)
                }
            }
        }
    }

    object None
}