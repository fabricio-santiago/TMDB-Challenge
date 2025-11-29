package com.santiago.fabricio.tmdbchallenge.core.data.remote.service.util

sealed class ResultData<out T> {
    data class Success<out T>(val value: T) : ResultData<T>()
    data class Empty(val code: Int = 0) : ResultData<Nothing>()
    data class APIError(val apiException: APIException) : ResultData<Nothing>()
}

inline fun <T : Any> ResultData<T>.onSuccess(action: (T) -> Unit): ResultData<T> {
    if (this is ResultData.Success) {
        action(this.value)
    }
    return this
}

inline fun <T : Any> ResultData<T>.onError(action: (ResultData.APIError) -> Unit): ResultData<T> {
    if (this is ResultData.APIError) {
        action(this)
    }
    return this
}

inline fun <T : Any> ResultData<T>.onEmpty(action: (ResultData.Empty) -> Unit): ResultData<T> {
    if (this is ResultData.Empty) {
        action(this)
    }
    return this
}