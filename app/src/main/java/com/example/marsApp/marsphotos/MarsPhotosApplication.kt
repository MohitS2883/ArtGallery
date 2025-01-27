package com.example.marsApp.marsphotos

import android.app.Application
import com.example.marsApp.marsphotos.data.AppContainer
import com.example.marsApp.marsphotos.data.DefaultAppContainer

class MarsPhotosApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}