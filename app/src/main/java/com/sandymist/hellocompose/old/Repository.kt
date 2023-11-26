package com.sandymist.hellocompose.old

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlin.random.Random

class Repository {
    val items = Channel<String>(100)

    suspend fun getItems(offset: Int, count: Int) {
        (1..count).toList().map {
            val randomDelay = Random.nextLong(10, 25)
            delay(randomDelay)
            val index = offset + it
            items.send("Item $index")
        }
    }
}