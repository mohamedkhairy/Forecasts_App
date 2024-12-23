package com.example.network.di

import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.HttpResponseData
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    @Singleton
    fun client(): HttpClient {
        return HttpClient(Android) {
            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        explicitNulls = true
                        expectSuccess = false
                        ignoreUnknownKeys = true
                    }
                )
            }

            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }


//            HttpResponseValidator {
//                validateResponse { response: HttpResponse ->
//                    val statusCode = response.status
//                    Log.d("xxx", "HTTP status code: ${statusCode.value}")
//                    when (statusCode) {
//                        HttpStatusCode.OK,
//                        HttpStatusCode.Created,
//                        HttpStatusCode.Accepted -> response
//
//                        HttpStatusCode.BadRequest -> throw ErrorKtor("Resource not found (400)")
//                        else -> {}
//                    }
//                }
//                handleResponseExceptionWithRequest { exception, _ ->
//                    if (exception is ClientRequestException) {
//                        Log.d("xxx status", "${exception.response.status}")
//                        when (exception.response.status) {
//                            HttpStatusCode.NotFound -> throw Exception("Resource not found (404)")
//                            else -> throw exception
//                        }
//                    }
//                }
//            }

        }
    }
}

class ErrorKtor(override val message: String): Exception(message)