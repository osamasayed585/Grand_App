package com.osandroid.grandapp.utils

import android.app.Application
import com.osandroid.grandapp.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class MainApplication: Application() {

    @Inject
    lateinit var debugTree: Timber.DebugTree

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG){
            debugTree.let { Timber.plant(it) }
        }
    }
}