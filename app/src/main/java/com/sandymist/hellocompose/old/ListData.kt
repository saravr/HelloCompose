package com.sandymist.hellocompose.old

import android.util.Log
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.coroutines.CoroutineContext

class ListData {
    val names: MutableStateFlow<List<String>> = MutableStateFlow(listOf())
    private var offset = 0
    private var inProgress = false
    private val repository1 = Repository()
    private val repository2 = Repository()
    private val repository3 = Repository()

    suspend fun getList(count: Int, loadMore: Boolean, coroutineContext: CoroutineContext) {
        if (inProgress) {
            Log.e("++++", "++++ IN PROGRESS SKIP")
            return
        }
        inProgress = true

        Log.e("++++", "++++ GET LIST $count, loadMore $loadMore")
        if (!loadMore) {
            offset = 0
        }
/*
        val jobs = (1..count).toList().map {
            names.add("")
            CoroutineScope(coroutineContext).launch {
                val randomDelay = Random.nextLong(10, 25)
                delay(randomDelay)
                val index = offset + it
                names[index - 1] = "Item $index"
            }
        }
        jobs.joinAll()
*/
        CoroutineScope(coroutineContext).launch {
            repository2.getItems(offset = offset + (count / 3), count = count / 3)
        }
        CoroutineScope(coroutineContext).launch {
            repository1.getItems(offset = offset, count = count / 3)
        }
        CoroutineScope(coroutineContext).launch {
            repository3.getItems(offset = offset + (2 * (count / 3)), count = count / 3)
        }

        CoroutineScope(coroutineContext).launch {
            repeat(count) {
                repository1.items.receive()
            }
        }

        offset += count
        inProgress = false
        Log.e("++++", "++++ GET LIST DONE")
    }
}