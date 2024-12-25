package com.example.details.fakeData

import com.example.core.sharedData.details.CityForecast
import com.example.details.data.remote.dto.CityWeatherListResponse
import com.example.utils.core.jsonParse

object CityForecastDetailsValid {

    val expectedCode = "200"
    val validCity = "Cairo"


    val data = "{\n" +
            "    \"cod\": \"200\",\n" +
            "    \"message\": 0,\n" +
            "    \"cnt\": 7,\n" +
            "    \"list\": [\n" +
            "        {\n" +
            "            \"dt\": 1735041600,\n" +
            "            \"main\": {\n" +
            "                \"temp\": 292.57,\n" +
            "                \"feels_like\": 291.4,\n" +
            "                \"temp_min\": 292.49,\n" +
            "                \"temp_max\": 292.57,\n" +
            "                \"pressure\": 1016,\n" +
            "                \"sea_level\": 1016,\n" +
            "                \"grnd_level\": 1010,\n" +
            "                \"humidity\": 32,\n" +
            "                \"temp_kf\": 0.08\n" +
            "            },\n" +
            "            \"weather\": [\n" +
            "                {\n" +
            "                    \"id\": 800,\n" +
            "                    \"main\": \"Clear\",\n" +
            "                    \"description\": \"clear sky\",\n" +
            "                    \"icon\": \"01d\"\n" +
            "                }\n" +
            "            ],\n" +
            "            \"clouds\": {\n" +
            "                \"all\": 0\n" +
            "            },\n" +
            "            \"wind\": {\n" +
            "                \"speed\": 5.4,\n" +
            "                \"deg\": 211,\n" +
            "                \"gust\": 6.36\n" +
            "            },\n" +
            "            \"visibility\": 10000,\n" +
            "            \"pop\": 0,\n" +
            "            \"sys\": {\n" +
            "                \"pod\": \"d\"\n" +
            "            },\n" +
            "            \"dt_txt\": \"2024-12-24 12:00:00\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"dt\": 1735052400,\n" +
            "            \"main\": {\n" +
            "                \"temp\": 292.11,\n" +
            "                \"feels_like\": 290.92,\n" +
            "                \"temp_min\": 291.19,\n" +
            "                \"temp_max\": 292.11,\n" +
            "                \"pressure\": 1016,\n" +
            "                \"sea_level\": 1016,\n" +
            "                \"grnd_level\": 1010,\n" +
            "                \"humidity\": 33,\n" +
            "                \"temp_kf\": 0.92\n" +
            "            },\n" +
            "            \"weather\": [\n" +
            "                {\n" +
            "                    \"id\": 800,\n" +
            "                    \"main\": \"Clear\",\n" +
            "                    \"description\": \"clear sky\",\n" +
            "                    \"icon\": \"01d\"\n" +
            "                }\n" +
            "            ],\n" +
            "            \"clouds\": {\n" +
            "                \"all\": 0\n" +
            "            },\n" +
            "            \"wind\": {\n" +
            "                \"speed\": 4.35,\n" +
            "                \"deg\": 211,\n" +
            "                \"gust\": 6.23\n" +
            "            },\n" +
            "            \"visibility\": 10000,\n" +
            "            \"pop\": 0,\n" +
            "            \"sys\": {\n" +
            "                \"pod\": \"d\"\n" +
            "            },\n" +
            "            \"dt_txt\": \"2024-12-24 15:00:00\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"dt\": 1735063200,\n" +
            "            \"main\": {\n" +
            "                \"temp\": 290.21,\n" +
            "                \"feels_like\": 288.91,\n" +
            "                \"temp_min\": 289.03,\n" +
            "                \"temp_max\": 290.21,\n" +
            "                \"pressure\": 1017,\n" +
            "                \"sea_level\": 1017,\n" +
            "                \"grnd_level\": 1011,\n" +
            "                \"humidity\": 36,\n" +
            "                \"temp_kf\": 1.18\n" +
            "            },\n" +
            "            \"weather\": [\n" +
            "                {\n" +
            "                    \"id\": 800,\n" +
            "                    \"main\": \"Clear\",\n" +
            "                    \"description\": \"clear sky\",\n" +
            "                    \"icon\": \"01n\"\n" +
            "                }\n" +
            "            ],\n" +
            "            \"clouds\": {\n" +
            "                \"all\": 0\n" +
            "            },\n" +
            "            \"wind\": {\n" +
            "                \"speed\": 4.12,\n" +
            "                \"deg\": 182,\n" +
            "                \"gust\": 7.57\n" +
            "            },\n" +
            "            \"visibility\": 10000,\n" +
            "            \"pop\": 0,\n" +
            "            \"sys\": {\n" +
            "                \"pod\": \"n\"\n" +
            "            },\n" +
            "            \"dt_txt\": \"2024-12-24 18:00:00\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"dt\": 1735074000,\n" +
            "            \"main\": {\n" +
            "                \"temp\": 287.64,\n" +
            "                \"feels_like\": 286.27,\n" +
            "                \"temp_min\": 287.64,\n" +
            "                \"temp_max\": 287.64,\n" +
            "                \"pressure\": 1017,\n" +
            "                \"sea_level\": 1017,\n" +
            "                \"grnd_level\": 1012,\n" +
            "                \"humidity\": 43,\n" +
            "                \"temp_kf\": 0\n" +
            "            },\n" +
            "            \"weather\": [\n" +
            "                {\n" +
            "                    \"id\": 800,\n" +
            "                    \"main\": \"Clear\",\n" +
            "                    \"description\": \"clear sky\",\n" +
            "                    \"icon\": \"01n\"\n" +
            "                }\n" +
            "            ],\n" +
            "            \"clouds\": {\n" +
            "                \"all\": 0\n" +
            "            },\n" +
            "            \"wind\": {\n" +
            "                \"speed\": 5.06,\n" +
            "                \"deg\": 184,\n" +
            "                \"gust\": 10.24\n" +
            "            },\n" +
            "            \"visibility\": 10000,\n" +
            "            \"pop\": 0,\n" +
            "            \"sys\": {\n" +
            "                \"pod\": \"n\"\n" +
            "            },\n" +
            "            \"dt_txt\": \"2024-12-24 21:00:00\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"dt\": 1735084800,\n" +
            "            \"main\": {\n" +
            "                \"temp\": 286.41,\n" +
            "                \"feels_like\": 284.97,\n" +
            "                \"temp_min\": 286.41,\n" +
            "                \"temp_max\": 286.41,\n" +
            "                \"pressure\": 1016,\n" +
            "                \"sea_level\": 1016,\n" +
            "                \"grnd_level\": 1010,\n" +
            "                \"humidity\": 45,\n" +
            "                \"temp_kf\": 0\n" +
            "            },\n" +
            "            \"weather\": [\n" +
            "                {\n" +
            "                    \"id\": 800,\n" +
            "                    \"main\": \"Clear\",\n" +
            "                    \"description\": \"clear sky\",\n" +
            "                    \"icon\": \"01n\"\n" +
            "                }\n" +
            "            ],\n" +
            "            \"clouds\": {\n" +
            "                \"all\": 0\n" +
            "            },\n" +
            "            \"wind\": {\n" +
            "                \"speed\": 5.39,\n" +
            "                \"deg\": 182,\n" +
            "                \"gust\": 10.9\n" +
            "            },\n" +
            "            \"visibility\": 10000,\n" +
            "            \"pop\": 0,\n" +
            "            \"sys\": {\n" +
            "                \"pod\": \"n\"\n" +
            "            },\n" +
            "            \"dt_txt\": \"2024-12-25 00:00:00\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"dt\": 1735095600,\n" +
            "            \"main\": {\n" +
            "                \"temp\": 285.81,\n" +
            "                \"feels_like\": 284.31,\n" +
            "                \"temp_min\": 285.81,\n" +
            "                \"temp_max\": 285.81,\n" +
            "                \"pressure\": 1015,\n" +
            "                \"sea_level\": 1015,\n" +
            "                \"grnd_level\": 1009,\n" +
            "                \"humidity\": 45,\n" +
            "                \"temp_kf\": 0\n" +
            "            },\n" +
            "            \"weather\": [\n" +
            "                {\n" +
            "                    \"id\": 800,\n" +
            "                    \"main\": \"Clear\",\n" +
            "                    \"description\": \"clear sky\",\n" +
            "                    \"icon\": \"01n\"\n" +
            "                }\n" +
            "            ],\n" +
            "            \"clouds\": {\n" +
            "                \"all\": 0\n" +
            "            },\n" +
            "            \"wind\": {\n" +
            "                \"speed\": 5.8,\n" +
            "                \"deg\": 176,\n" +
            "                \"gust\": 11.9\n" +
            "            },\n" +
            "            \"visibility\": 10000,\n" +
            "            \"pop\": 0,\n" +
            "            \"sys\": {\n" +
            "                \"pod\": \"n\"\n" +
            "            },\n" +
            "            \"dt_txt\": \"2024-12-25 03:00:00\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"dt\": 1735106400,\n" +
            "            \"main\": {\n" +
            "                \"temp\": 286.23,\n" +
            "                \"feels_like\": 284.69,\n" +
            "                \"temp_min\": 286.23,\n" +
            "                \"temp_max\": 286.23,\n" +
            "                \"pressure\": 1015,\n" +
            "                \"sea_level\": 1015,\n" +
            "                \"grnd_level\": 1010,\n" +
            "                \"humidity\": 42,\n" +
            "                \"temp_kf\": 0\n" +
            "            },\n" +
            "            \"weather\": [\n" +
            "                {\n" +
            "                    \"id\": 800,\n" +
            "                    \"main\": \"Clear\",\n" +
            "                    \"description\": \"clear sky\",\n" +
            "                    \"icon\": \"01d\"\n" +
            "                }\n" +
            "            ],\n" +
            "            \"clouds\": {\n" +
            "                \"all\": 0\n" +
            "            },\n" +
            "            \"wind\": {\n" +
            "                \"speed\": 6.67,\n" +
            "                \"deg\": 181,\n" +
            "                \"gust\": 12.97\n" +
            "            },\n" +
            "            \"visibility\": 10000,\n" +
            "            \"pop\": 0,\n" +
            "            \"sys\": {\n" +
            "                \"pod\": \"d\"\n" +
            "            },\n" +
            "            \"dt_txt\": \"2024-12-25 06:00:00\"\n" +
            "        }\n" +
            "    ],\n" +
            "    \"city\": {\n" +
            "        \"id\": 360630,\n" +
            "        \"name\": \"Cairo\",\n" +
            "        \"coord\": {\n" +
            "            \"lat\": 30.0626,\n" +
            "            \"lon\": 31.2497\n" +
            "        },\n" +
            "        \"country\": \"EG\",\n" +
            "        \"population\": 7734614,\n" +
            "        \"timezone\": 7200,\n" +
            "        \"sunrise\": 1735015702,\n" +
            "        \"sunset\": 1735052453\n" +
            "    }\n" +
            "}"

    val emptyWeatherListResult = "{\n" +
            "    \"cod\": \"200\",\n" +
            "    \"message\": 0,\n" +
            "    \"cnt\": 7,\n" +
            "    \"list\": [],\n" +
            "    \"city\": {\n" +
            "        \"id\": 360630,\n" +
            "        \"name\": \"Cairo\",\n" +
            "        \"coord\": {\n" +
            "            \"lat\": 30.0626,\n" +
            "            \"lon\": 31.2497\n" +
            "        },\n" +
            "        \"country\": \"EG\",\n" +
            "        \"population\": 7734614,\n" +
            "        \"timezone\": 7200,\n" +
            "        \"sunrise\": 1735015702,\n" +
            "        \"sunset\": 1735052453\n" +
            "    }\n" +
            "}"


//    val cityForecast = "{\"city\":\"Cairo\",\"cityId\":360630,\"cityWeatherList\":[{\"date\":\"2024-12-25 12:00:00\",\"humidity\":\"25\",\"icon\":\"http://openweathermap.org/img/wn/01d@2x.png\",\"main\":\"Clear\",\"temp\":\"293.71\",\"tempMax\":\"294.0\",\"tempMin\":\"293.71\"},{\"date\":\"2024-12-25 15:00:00\",\"humidity\":\"26\",\"icon\":\"http://openweathermap.org/img/wn/01d@2x.png\",\"main\":\"Clear\",\"temp\":\"292.49\",\"tempMax\":\"292.49\",\"tempMin\":\"291.95\"},{\"date\":\"2024-12-25 18:00:00\",\"humidity\":\"33\",\"icon\":\"http://openweathermap.org/img/wn/01n@2x.png\",\"main\":\"Clear\",\"temp\":\"288.01\",\"tempMax\":\"288.01\",\"tempMin\":\"288.01\"},{\"date\":\"2024-12-25 21:00:00\",\"humidity\":\"30\",\"icon\":\"http://openweathermap.org/img/wn/01n@2x.png\",\"main\":\"Clear\",\"temp\":\"286.13\",\"tempMax\":\"286.13\",\"tempMin\":\"286.13\"},{\"date\":\"2024-12-26 00:00:00\",\"humidity\":\"31\",\"icon\":\"http://openweathermap.org/img/wn/01n@2x.png\",\"main\":\"Clear\",\"temp\":\"284.95\",\"tempMax\":\"284.95\",\"tempMin\":\"284.95\"},{\"date\":\"2024-12-26 03:00:00\",\"humidity\":\"34\",\"icon\":\"http://openweathermap.org/img/wn/01n@2x.png\",\"main\":\"Clear\",\"temp\":\"284.06\",\"tempMax\":\"284.06\",\"tempMin\":\"284.06\"},{\"date\":\"2024-12-26 06:00:00\",\"humidity\":\"37\",\"icon\":\"http://openweathermap.org/img/wn/01d@2x.png\",\"main\":\"Clear\",\"temp\":\"284.45\",\"tempMax\":\"284.45\",\"tempMin\":\"284.45\"}],\"country\":\"EG\"}"

    val cityForecast = "{\"country\":\"EG\",\"city\":\"Cairo\",\"cityId\":360630,\"cityWeatherList\":[{\"humidity\":\"32\",\"temp\":\"292.57\",\"tempMax\":\"292.57\",\"tempMin\":\"292.49\",\"date\":\"2024-12-24 12:00:00\",\"icon\":\"http://openweathermap.org/img/wn/01d@2x.png\",\"main\":\"Clear\"},{\"humidity\":\"33\",\"temp\":\"292.11\",\"tempMax\":\"292.11\",\"tempMin\":\"291.19\",\"date\":\"2024-12-24 15:00:00\",\"icon\":\"http://openweathermap.org/img/wn/01d@2x.png\",\"main\":\"Clear\"},{\"humidity\":\"36\",\"temp\":\"290.21\",\"tempMax\":\"290.21\",\"tempMin\":\"289.03\",\"date\":\"2024-12-24 18:00:00\",\"icon\":\"http://openweathermap.org/img/wn/01n@2x.png\",\"main\":\"Clear\"},{\"humidity\":\"43\",\"temp\":\"287.64\",\"tempMax\":\"287.64\",\"tempMin\":\"287.64\",\"date\":\"2024-12-24 21:00:00\",\"icon\":\"http://openweathermap.org/img/wn/01n@2x.png\",\"main\":\"Clear\"},{\"humidity\":\"45\",\"temp\":\"286.41\",\"tempMax\":\"286.41\",\"tempMin\":\"286.41\",\"date\":\"2024-12-25 00:00:00\",\"icon\":\"http://openweathermap.org/img/wn/01n@2x.png\",\"main\":\"Clear\"},{\"humidity\":\"45\",\"temp\":\"285.81\",\"tempMax\":\"285.81\",\"tempMin\":\"285.81\",\"date\":\"2024-12-25 03:00:00\",\"icon\":\"http://openweathermap.org/img/wn/01n@2x.png\",\"main\":\"Clear\"},{\"humidity\":\"42\",\"temp\":\"286.23\",\"tempMax\":\"286.23\",\"tempMin\":\"286.23\",\"date\":\"2024-12-25 06:00:00\",\"icon\":\"http://openweathermap.org/img/wn/01d@2x.png\",\"main\":\"Clear\"}]}"


    fun getCityWeatherListResponse(): CityWeatherListResponse = data.jsonParse<CityWeatherListResponse>()

    fun getEmptyWeatherListResultResponse(): CityWeatherListResponse = emptyWeatherListResult.jsonParse<CityWeatherListResponse>()

    fun getCityForecast() = cityForecast.jsonParse<CityForecast>()



}