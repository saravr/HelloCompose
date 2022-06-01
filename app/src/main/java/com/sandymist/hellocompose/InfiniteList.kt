package com.sandymist.hellocompose

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.flow.distinctUntilChanged

@Preview(showSystemUi = true)
@Composable
fun InfiniteList() {
    val list = ListData()
    //val value = list.names.collectAsState(initial = listOf())
    val listState = rememberLazyListState()

    LaunchedEffect(key1 = true) {
        list.getList(30, false)
    }

    val loadMore = remember {
        derivedStateOf {
            val layoutInfo = listState.layoutInfo
            val totalItemsNumber = layoutInfo.totalItemsCount
            val lastVisibleItemIndex = (layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0) + 1

            val status = lastVisibleItemIndex > (totalItemsNumber - 7)
            Log.e("++++", "LOAD MORE? $status: [$lastVisibleItemIndex > ($totalItemsNumber - 7)]")
            status
        }
    }

    LaunchedEffect(loadMore) {
        snapshotFlow { loadMore.value }
            //.distinctUntilChanged()
            .collect {
                Log.e("++++", "++++ COLLECT LOAD MORE $it")
                if (it) {
                    list.getList(30, true)
                }
            }
    }

    LazyColumn(
        state = listState
    ) {
        items(items = list.names) { name ->
            Box(
                modifier = Modifier
                    .padding(20.dp)
                    .height(30.dp)
                    .fillMaxWidth()
            ) {
                Text("Item $name ...", style = TextStyle(fontSize = 16.sp))
            }
        }
    }
}