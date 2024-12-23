package com.example.utils.handler

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow

inline fun <ResultType, RequestType> cachingHandler(
    crossinline localSource: (isSuccess: Boolean) -> Flow<ResultType>,
    crossinline remoteSource: suspend () -> RequestType,
    crossinline saveResult: suspend (RequestType) -> Unit,
) = flow {

    val data = try {
        val remoteData = remoteSource()
        saveResult(remoteData)
        localSource(true)
    } catch (throwable: Throwable) {
        throwable.printStackTrace()
        localSource(false)
    }
    emitAll(data)
}