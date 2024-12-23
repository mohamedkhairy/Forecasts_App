package com.example.home.fakeData

import com.example.core.sharedData.CityWeather
import com.example.database.entity.WeatherEntity
import com.example.home.data.remote.dto.CityWeatherResponse
import com.example.home.serializeWeatherData
import com.example.utils.core.jsonParse

object CityWeatherValid {

    val expectedCode = 200
    val validCity = "Cairo"


    val data = "{\n" +
            "    \"coord\": {\n" +
            "        \"lon\": 31.2497,\n" +
            "        \"lat\": 30.0626\n" +
            "    },\n" +
            "    \"weather\": [\n" +
            "        {\n" +
            "            \"id\": 804,\n" +
            "            \"main\": \"Clouds\",\n" +
            "            \"description\": \"overcast clouds\",\n" +
            "            \"icon\": \"04d\"\n" +
            "        }\n" +
            "    ],\n" +
            "    \"base\": \"stations\",\n" +
            "    \"main\": {\n" +
            "        \"temp\": 294.57,\n" +
            "        \"feels_like\": 293.5,\n" +
            "        \"temp_min\": 293.94,\n" +
            "        \"temp_max\": 294.57,\n" +
            "        \"pressure\": 1019,\n" +
            "        \"humidity\": 28,\n" +
            "        \"sea_level\": 1019,\n" +
            "        \"grnd_level\": 1013\n" +
            "    },\n" +
            "    \"visibility\": 6000,\n" +
            "    \"wind\": {\n" +
            "        \"speed\": 1.54,\n" +
            "        \"deg\": 260\n" +
            "    },\n" +
            "    \"clouds\": {\n" +
            "        \"all\": 100\n" +
            "    },\n" +
            "    \"dt\": 1734873455,\n" +
            "    \"sys\": {\n" +
            "        \"type\": 1,\n" +
            "        \"id\": 2514,\n" +
            "        \"country\": \"EG\",\n" +
            "        \"sunrise\": 1734842849,\n" +
            "        \"sunset\": 1734879590\n" +
            "    },\n" +
            "    \"timezone\": 7200,\n" +
            "    \"id\": 360630,\n" +
            "    \"name\": \"Cairo\",\n" +
            "    \"cod\": 200\n" +
            "}"

    val emptyCharactersResult = "{\n" +
            "  \"attributionText\": \"Data provided by Marvel. © 2024 MARVEL\",\n" +
            "  \"code\": 200,\n" +
            "  \"emptyData\": {\n" +
            "    \"count\": 10,\n" +
            "    \"limit\": 10,\n" +
            "    \"offset\": 0,\n" +
            "    \"results\": [],\n" +
            "    \"total\": 1564\n" +
            "  },\n" +
            "  \"status\": \"Ok\"\n" +
            "}"



    val cityWeather = "{\"city\":\"Cairo\",\"cityId\":360630,\"country\":\"EG\",\"main\":{\"humidity\":\"34\",\"temp\":\"292.57\",\"tempMax\":\"292.57\",\"tempMin\":\"292.27\"},\"weather\":[{\"description\":\"sand\",\"icon\":\"http://openweathermap.org/img/wn/50n@2x.png\",\"main\":\"Sand\"}]}"

    val weatherEntityData = "{\"city\":\"Cairo\",\"cityId\":360630,\"country\":\"EG\",\"main\":{\"humidity\":\"34\",\"temp\":\"292.57\",\"tempMax\":\"292.57\",\"tempMin\":\"292.27\"},\"weatherDetails\":[{\"description\":\"sand\",\"icon\":\"http://openweathermap.org/img/wn/50n@2x.png\",\"main\":\"Sand\"}]}"


    fun getCityWeatherResponse(): CityWeatherResponse = serializeWeatherData(data)

    fun getCityWeather() = cityWeather.jsonParse<CityWeather>()

    fun getWeatherEntity() = weatherEntityData.jsonParse<WeatherEntity>()


}