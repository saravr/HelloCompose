package com.sandymist.hellocompose

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import kotlinx.coroutines.delay

class ListData {
    val names = mutableStateListOf<String>()
    private var offset = 0
    private var inProgress = false

    suspend fun getList(count: Int, loadMore: Boolean) {
        if (inProgress) {
            Log.e("++++", "++++ IN PROG SKIP")
            return
        }
        inProgress = true

        Log.e("++++", "++++ GET LIST $count, loadMore $loadMore")
        if (!loadMore) {
            offset = 0
        }

        repeat(count) {
            delay(100)
            val index = offset + it
            names.add( "Item $index")
        }

        offset += count
        inProgress = false
        Log.e("++++", "++++ GET LIST DONE")
    }
}