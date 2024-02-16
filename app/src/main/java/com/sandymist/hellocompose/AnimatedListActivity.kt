package com.sandymist.hellocompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sandymist.hellocompose.ui.theme.HelloComposeTheme
import kotlin.random.Random

private const val NumItems = 10

class AnimatedListActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HelloComposeTheme {
                val listItems = remember {
                    List(NumItems) { index -> Item(index) }
                        .toMutableStateList()
                }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    AnimatedListScreen(listItems = listItems)
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AnimatedListScreen(listItems: SnapshotStateList<Item>) {
    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .weight(1f),
        ) {
            itemsIndexed(
                items = listItems,
                key = { _, item -> item.id },
            ) { _, item ->
                ListItem(
                    label = item.label,
                    color = item.color,
                    modifier = Modifier
                        .animateItemPlacement(
                            animationSpec = tween(
                                durationMillis = 500,
                                easing = LinearOutSlowInEasing,
                            )
                        )
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    listItems.shuffle()
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp),
            ) {
                Text("Shuffle")
            }

            Button(
                onClick = {
                    val newIndex = if (listItems.isEmpty()) {
                        0
                    } else {
                        listItems.maxOf { it.id } + 1
                    }
                    listItems.add(
                        index = Random.nextInt(listItems.size),
                        Item(newIndex),
                    )
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp),
            ) {
                Text("Add")
            }

            Button(
                onClick = {
                    val idToRemove = listItems.minOf { it.id }
                    val item = listItems.first { it.id == idToRemove }
                    listItems.remove(item)
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp),
                enabled = listItems.isNotEmpty()
            ) {
                Text("Remove")
            }
        }
    }
}

data class Item(
    val id: Int,
    val label: String,
    val color: Color,
)

private val colors = listOf(
    Color.Red,
    Color.Blue,
    Color.Green,
    Color.Magenta,
    Color.Cyan,
)

fun Item(index: Int) = Item(
    id = index,
    label = "Item $index",
    color = colors[index % colors.size],
)

@Composable
fun ListItem(
    label: String,
    color: Color,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .background(color = color)
            .padding(60.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.body1,
        )
    }
}

@Preview(widthDp = 320)
@Composable
fun PreviewListItem() {
    HelloComposeTheme {
        Surface {
            ListItem(
                label = "Item 1",
                color = Color.Red,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 16.dp)
                    .height(40.dp),
            )
        }
    }
}