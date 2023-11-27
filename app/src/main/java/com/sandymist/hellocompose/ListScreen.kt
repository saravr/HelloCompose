package com.sandymist.hellocompose

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sandymist.hellocompose.viewmodel.NamesViewModel

private const val TAG = "ListScreen"

@Composable
fun ListScreen(namesViewModel: NamesViewModel) {
    val values = namesViewModel.names.collectAsState()

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 20.dp, vertical = 10.dp)
    ) {
        AddBox {
            namesViewModel.add(it)
        }
        LazyColumn {
            items(values.value) {
                key(it) {
                    Name(it)
                    Divider()
                }
            }
        }
    }
}

@Composable
fun AddBox(onClick: (String) -> Unit) {
    Button(modifier = Modifier.fillMaxWidth(), onClick = { onClick("New stuff") }) {
        Text("Add")
    }
}

@Composable
fun Name(name: String) {
    Log.e(TAG, "++++ Comp: Name $name")
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 10.dp)) {
        Text(name, style = MaterialTheme.typography.h5)
    }
}

/*
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
*/