package com.example.amphibian

import android.app.Application
import com.example.amphibian.data.AppContainer
import com.example.amphibian.data.DefaultAppContainer

class AmphibianInfoApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}