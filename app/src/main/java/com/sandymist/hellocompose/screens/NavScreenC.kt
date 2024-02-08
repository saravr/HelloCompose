package com.sandymist.hellocompose.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun NavScreenC(
    previousRoute: String,
    goBack: () -> Unit,
    navigateToC1: () -> Unit,
    navigateToWeb: () -> Unit,
) {
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Nav C", style = MaterialTheme.typography.h1)
            Text(previousRoute, style = MaterialTheme.typography.subtitle1)
            Button(
                onClick = {
                    scope.launch {
                        delay(1000)
                        navigateToC1()
                    }
                }
            ) {
                Text("Navigate to C1")
            }
            Button(onClick = {
                navigateToWeb()
            }) {
                Text("Visit a website")
            }
            Button(onClick = { goBack() }) {
                Text("Go back")
            }
        }
    }
}

@Preview
@Composable
fun PreviewNavScreenC() {
    //NavScreenC({}, {})
}