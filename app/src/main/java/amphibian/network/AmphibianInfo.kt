package com.example.amphibian.network
import com.google.gson.annotations.SerializedName

data class AmphibianInfo(
    val name: String,
    val type: String,
    val description: String,
    @SerializedName("img_src")
    val imgSrc: String
)
