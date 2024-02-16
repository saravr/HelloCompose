package com.sandymist.hellocompose.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.sandymist.hellocompose.viewmodel.NamesViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun ListScreen(namesViewModel: NamesViewModel) {
    val values = namesViewModel.names.collectAsState()
    val scope = rememberCoroutineScope()

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 20.dp, vertical = 10.dp)
    ) {
        AddBox {
            namesViewModel.add(it)
        }
        LazyColumn {
            items(values.value) {
                Row(
                    modifier = Modifier
                        .animateItemPlacement(
                            animationSpec = tween(
                                durationMillis = 5500,
                                easing = LinearOutSlowInEasing,
                            )
                        )
                        .padding(horizontal = 10.dp, vertical = 60.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(it, style = MaterialTheme.typography.h5, modifier = Modifier.weight(1f))
                    IconButton(
                        onClick = {
                            scope.launch {
                                //namesViewModel.remove(it)
                            }
                        }
                    ) {
                        Icon(imageVector = Icons.Filled.Delete, contentDescription = "", tint = Color.Red)
                    }
                }
                Divider()
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
