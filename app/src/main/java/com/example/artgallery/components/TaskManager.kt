package com.example.artgallery.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artgallery.R
import com.example.artgallery.ui.theme.ArtGalleryTheme

@Preview(showBackground = true)
@Composable
fun TaskManagerPreview() {
    ArtGalleryTheme {
        CentrePart(
            msg = "All tasks completed",
            msg2 = "Nice work!",
        )
    }
}

@Composable
fun CentrePart(msg: String, msg2: String, modifier: Modifier = Modifier) {
    val image = painterResource(R.drawable.ic_task_completed)
    Column(
        modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
        )
        Text(
            text = msg,
            fontSize = 20.sp,
            lineHeight = 16.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .padding(
                    top = 24.dp, bottom = 8.dp
                )
        )
        Text(
            text = msg2,
            fontSize = 30.sp,
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally)

        )
    }
}