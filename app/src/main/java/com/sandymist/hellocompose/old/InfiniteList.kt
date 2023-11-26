package com.sandymist.hellocompose.old

import android.util.Log
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.flow.distinctUntilChanged

data class ScrollInfo(
    val lastVisibleItemIndex: Int,
    val totalItemsNumber: Int
)

@Preview(showSystemUi = true)
@Composable
fun InfiniteList() {
    val list = ListData()
    val value = list.names.collectAsState(initial = listOf())
    val listState = rememberLazyListState()

    LaunchedEffect(key1 = true) {
        list.getList(30, false, coroutineContext)
    }

    val loadMore = remember {
        derivedStateOf {
            val layoutInfo = listState.layoutInfo
            val totalItemsNumber = layoutInfo.totalItemsCount
            val lastVisibleItemIndex = (layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0) + 1

            ScrollInfo(lastVisibleItemIndex, totalItemsNumber)
        }
    }

    LaunchedEffect(loadMore) {
        snapshotFlow { loadMore.value }
            .distinctUntilChanged()
            .collect {
                Log.e("++++", "++++ COLLECT LOAD MORE $it")
                val getMore = it.lastVisibleItemIndex > (it.totalItemsNumber - 7)
                if (getMore) {
                    list.getList(30, true, coroutineContext)
                }
            }
    }
/*
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
    }*/
}