package com.example.utils.handler

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow

inline fun <ResultType, RequestType> cachingHandler(
    crossinline localSource: () -> Flow<ResultType>,
    crossinline remoteSource: suspend () -> RequestType,
    crossinline saveResult: suspend (RequestType) -> Unit,
) = flow {

    val data = try {
        val remoteData = remoteSource()
        saveResult(remoteData)
        localSource()
    } catch (throwable: Throwable) {
        throwable.printStackTrace()
        localSource()
    }
    emitAll(data)
}