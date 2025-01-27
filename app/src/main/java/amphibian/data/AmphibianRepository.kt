package com.example.amphibian.data

import com.example.amphibian.network.AmphibianApiService
import com.example.amphibian.network.AmphibianInfo

interface AmphibianRepository {
    suspend fun getInfo(): List<AmphibianInfo>
}


class AmphibianInfoRepository(
    private val amphibianApiService: AmphibianApiService
) : AmphibianRepository {
    override suspend fun getInfo(): List<AmphibianInfo> = amphibianApiService.getInfo()
}