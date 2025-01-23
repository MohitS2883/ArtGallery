package com.example.artgallery.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val stringResourceId: Int,
    val noOfEnrolled: Int,
    @DrawableRes val imageResourceId: Int
)
