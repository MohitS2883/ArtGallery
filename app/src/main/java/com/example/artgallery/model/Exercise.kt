package com.example.fitnessapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Exercise(
    @StringRes val dayRes: Int,
    @StringRes val titleRes: Int,
    @DrawableRes val imageRes: Int,
    @StringRes val descriptionRes: Int
)
