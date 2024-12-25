package com.example.details.screens.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.sharedData.details.CityWeatherDetails
import com.example.core.ui.component.CoilImagePainter

@Composable
fun WeatherDetailsCard(
    cityWeatherDetails: CityWeatherDetails
) {

    Card(
        border = BorderStroke(1.dp, Color.Gray),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .padding(8.dp),
            Arrangement.Center
        ) {

            Image(
                painter = CoilImagePainter(cityWeatherDetails.icon),
                contentDescription = cityWeatherDetails.main,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(30.dp)
            )

                Text(
                    text = cityWeatherDetails.main ?: "-",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center
                )



            Row {

                Text(
                    text = "Temperature: ${cityWeatherDetails.temp}",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 12.sp,
                )

                VerticalDivider(
                    color = Color.Gray,
                    thickness = 5.dp
                )

                Text(
                    text = "Humidity: ${cityWeatherDetails.humidity}",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 12.sp,
                )

            }



            Text(
                text = "Date: ${cityWeatherDetails.date}",
                color = MaterialTheme.colorScheme.primary,
                fontSize = 12.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .border(border = BorderStroke(1.dp, Color.LightGray))
            )


        }
    }


}