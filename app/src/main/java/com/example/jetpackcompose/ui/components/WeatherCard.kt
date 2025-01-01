package com.example.jetpackcompose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.jetpackcompose.data.ForecastItem
import com.example.jetpackcompose.ui.views.convertUnixToTime

@Composable
fun WeatherCard(forecastItem: ForecastItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .padding(horizontal = 16.dp)
            .background(color = Color(0xFFBBDEFB), shape = RoundedCornerShape(16.dp))
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberAsyncImagePainter("https://openweathermap.org/img/wn/${forecastItem.weather.firstOrNull()?.icon}@2x.png"),
            contentDescription = null,
            modifier = Modifier.size(100.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(24.dp))

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = convertUnixToTime(forecastItem.dt),
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp),
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "${forecastItem.main.temp}°C - ${forecastItem.weather.firstOrNull()?.description ?: "N/A"}",
                color = Color.Gray,
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 18.sp)
            )
        }
    }
}
