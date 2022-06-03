package com.sandymist.hellocompose

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.random.Random

class ListData {
    val names = mutableStateListOf<String>()
    private var offset = 0
    private var inProgress = false

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

        offset += count
        inProgress = false
        Log.e("++++", "++++ GET LIST DONE")
    }
}