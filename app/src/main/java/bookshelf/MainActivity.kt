package com.example.bookshelf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import bookshelf.ui.screens.BookApp
import com.example.bookshelf.ui.theme.BookShelfTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            BookShelfTheme {
                BookApp()
            }
        }
    }
/*
    private fun enableEdgeToEdge() {
        // Disable system window decor fitting
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = Color.parseColor("#964B00")

        // Use WindowInsetsControllerCompat for controlling system bar appearance
        val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
        if (windowInsetsController != null) {
            // Set light status bar icons (true for light icons on a dark background)
            windowInsetsController.isAppearanceLightStatusBars = false

            // Optionally, set light navigation bar icons
            windowInsetsController.isAppearanceLightNavigationBars = false
        }
    }*/
}