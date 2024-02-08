package com.sandymist.hellocompose.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun NavScreenB(
    goBack: () -> Unit,
    navigateToC: () -> Unit,

) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Nav B", style = MaterialTheme.typography.h1)
            Button(onClick = { navigateToC() }) {
                Text("Navigate to C")
            }
            Button(onClick = { goBack() }) {
                Text("Go back")
            }
        }
    }
}

@Preview
@Composable
fun PreviewNavScreenB() {
    NavScreenB({}, {})
}