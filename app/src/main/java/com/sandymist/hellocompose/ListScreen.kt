package com.sandymist.hellocompose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ListScreen() {
    var counter by remember { mutableIntStateOf(10) }

    println("++++ Comp: ListScreen")
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Test("Joe", "Black $counter")
        Button(onClick = {
            counter++
        }) {
            Text("Update")
        }
    }
}

@Composable
fun Test(firstName: String, lastName: String) {
    println("++++ Comp: Test")
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ShowName("First name", firstName)
        ShowName("Last name", lastName)
    }
}

@Composable
fun ShowName(label: String, name: String) {
    println("++++ Comp: Showname for $label")
    Text("$label: $name", style = MaterialTheme.typography.h5)
    Spacer(modifier = Modifier.height(20.dp))
}