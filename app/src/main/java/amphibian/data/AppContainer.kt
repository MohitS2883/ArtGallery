package com.example.amphibian.data

import com.example.amphibian.network.AmphibianApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
    val amphibianInfoRepository: AmphibianRepository
}

class DefaultAppContainer : AppContainer {
    private val baseUrl =
        "https://android-kotlin-fun-mars-server.appspot.com/"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: AmphibianApiService by lazy {
        retrofit.create(AmphibianApiService::class.java)
    }


    override val amphibianInfoRepository: AmphibianRepository by lazy {
        AmphibianInfoRepository(retrofitService)
    }

}
