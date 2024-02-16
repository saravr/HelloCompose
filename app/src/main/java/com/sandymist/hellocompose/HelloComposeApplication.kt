package com.sandymist.hellocompose

import android.app.Application
import timber.log.Timber

class HelloComposeApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant()
    }
}
