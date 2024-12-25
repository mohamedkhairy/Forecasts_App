package com.example.details.engine

import com.example.details.data.remote.service.details.CityWeatherListService
import com.example.details.data.remote.service.details.CityWeatherListServiceImpl
import com.example.details.fakeData.CityForecastDetailsEmpty
import com.example.details.fakeData.CityForecastDetailsInvalid
import com.example.details.fakeData.CityForecastDetailsValid
import com.example.details.fakeData.CityForecastDetailsValid.validCity
import com.example.network.remoteBase.Endpoints
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.engine.mock.respondBadRequest
import io.ktor.client.engine.mock.respondError
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.HttpStatusCode
import io.ktor.http.Url
import io.ktor.http.fullPath
import io.ktor.http.headersOf
import io.ktor.http.hostWithPort
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class ForecastFakeClient {

    companion object Factory {

        private val Url.hostWithPortIfRequired: String get() = if (port == protocol.defaultPort) host else hostWithPort
        private val Url.fullUrl: String get() = "${protocol.name}://$hostWithPortIfRequired$fullPath"

        fun build(
            type: ServiceResponseType
        ): CityWeatherListService {
            val client = HttpClient(MockEngine) {
                install(ContentNegotiation) {
                    json(
                        Json {
                            prettyPrint = true
                            isLenient = true
                            ignoreUnknownKeys = true
                        }
                    )
                }
                engine {
                    addHandler { request ->
                        when (request.url.fullUrl) {
                            Endpoints.CITY_WEATHER_LIST_API(validCity).fullUrl -> {
                                val responseHeaders = headersOf(
                                    "Content-Type" to listOf("application/json", "charset=utf-8")
                                )
                                when(type){
                                    is ServiceResponseType.EmptyResponse -> {
                                        respond(
                                            CityForecastDetailsEmpty.emptyJson,
                                            status = HttpStatusCode.OK,
                                            headers = responseHeaders
                                        )
                                    }
                                    is ServiceResponseType.EmptyResultList -> {
                                        respond(
                                            CityForecastDetailsValid.emptyWeatherListResult,
                                            status = HttpStatusCode.OK,
                                            headers = responseHeaders
                                        )
                                    }
                                    is ServiceResponseType.InvalidData -> {
                                        respond(
                                            CityForecastDetailsInvalid.data,
                                            status = HttpStatusCode.OK,
                                            headers = responseHeaders
                                        )
                                    }
                                    is ServiceResponseType.GoodData -> {
                                        respond(
                                            CityForecastDetailsValid.data,
                                            status = HttpStatusCode.OK,
                                            headers = responseHeaders
                                        )
                                    }
                                    is ServiceResponseType.Http404 -> {
                                        respondError(
                                            status = HttpStatusCode.NotFound,
                                            headers = responseHeaders
                                        )
                                    }
                                }
                            }
                            else -> respondBadRequest()
                        }
                    }
                }
            }
            return CityWeatherListServiceImpl(client)
        }
    }
}