package com.example.home.engine

import com.example.home.data.remote.service.weather.CityWeatherService
import com.example.home.data.remote.service.weather.CityWeatherServiceImpl
import com.example.home.fakeData.CityWeatherEmpty
import com.example.home.fakeData.CityWeatherInvalid
import com.example.home.fakeData.CityWeatherValid
import com.example.home.fakeData.CityWeatherValid.validCity
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
        ): CityWeatherService {
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
                            Endpoints.WEATHER_API(validCity).fullUrl -> {
                                val responseHeaders = headersOf(
                                    "Content-Type" to listOf("application/json", "charset=utf-8")
                                )
                                when(type){
                                    is ServiceResponseType.EmptyResponse -> {
                                        respond(
                                            CityWeatherEmpty.emptyJson,
                                            status = HttpStatusCode.OK,
                                            headers = responseHeaders
                                        )
                                    }
                                    is ServiceResponseType.EmptyResultList -> {
                                        respond(
                                            CityWeatherValid.emptyCharactersResult,
                                            status = HttpStatusCode.OK,
                                            headers = responseHeaders
                                        )
                                    }
                                    is ServiceResponseType.InvalidData -> {
                                        respond(
                                            CityWeatherInvalid.data,
                                            status = HttpStatusCode.OK,
                                            headers = responseHeaders
                                        )
                                    }
                                    is ServiceResponseType.GoodData -> {
                                        respond(
                                            CityWeatherValid.data,
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
            return CityWeatherServiceImpl(client)
        }
    }
}