package com.example.core.ui.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun ImageFromURL(imageUrl: String?){
    val imageLoader = createInsecureImageLoader(LocalContext.current)

    AsyncImage(
        model = imageUrl,
        imageLoader = imageLoader,
//        placeholder = painterResource(android.R.drawable.ic_menu_gallery),
        contentDescription = "",
//        contentScale = ContentScale.Crop,
        modifier = Modifier.size(50.dp),
//        colorFilter = ColorFilter.tint(Color.Blue)
    )
}