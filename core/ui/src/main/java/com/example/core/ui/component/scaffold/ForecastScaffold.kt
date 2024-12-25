package com.example.core.ui.component.scaffold

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForecastScaffold(
    title: String,
    content: @Composable (padding: PaddingValues) -> Unit,
    icon: (@Composable () -> Unit)? = null,
    navIcon: (@Composable () -> Unit)? = null
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                        Text(
                            modifier = Modifier.padding(8.dp),
                            text = title,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                        )
                }, actions = {
                    icon?.invoke()
                },
                navigationIcon = {
                    navIcon?.invoke()
                }
            )
        },
    ) { innerPadding ->
        content(innerPadding)
    }
}