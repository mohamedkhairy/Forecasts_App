package com.example.core.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp

@Composable
fun AppLoading(isLoading: Boolean) {
    if (isLoading)
        Box(
            modifier = Modifier.fillMaxSize().testTag("loading"),
            contentAlignment = Alignment.Center
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent)
                    .alpha(0.5f),
            ){}
            CircularProgressIndicator(
                modifier = Modifier
                    .requiredWidth(40.dp)
                    .requiredHeight(40.dp),
                color = MaterialTheme.colorScheme.primary
            )
        }

}