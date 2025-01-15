package com.example.artgallery.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artgallery.R
import com.example.artgallery.ui.theme.ArtGalleryTheme

@Preview(showBackground = true)
@Composable
fun BasicImageTextAppPreview() {
    ArtGalleryTheme {
        BodyImage(
            msg = "Jetpack Compose tutorial"
        )
    }
}

@Composable
fun TextBody(msg: String, modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly, modifier = Modifier
    ) {
        Text(
            text = msg,
            modifier = modifier
                .padding(16.dp)
                .align(alignment = Alignment.Start),
            fontSize = 24.sp,
        )
        Text(
            text = "Jetpack Compose is a modern toolkit for building native Android UI. Compose simplifies and accelerates UI development on Android with less code, powerful tools, and intuitive Kotlin APIs.",
            modifier = modifier.padding(
                start = 16.dp, end = 16.dp
            ),
            textAlign = TextAlign.Justify
        )
        Text(
            text = "In this tutorial, you build a simple UI component with declarative functions. You call Compose functions to say what elements you want and the Compose compiler does the rest. Compose is built around Composable functions. These functions let you define your app's UI programmatically because they let you describe how it should look and provide data dependencies, rather than focus on the process of the UI's construction, such as initializing an element and then attaching it to a parent. To create a Composable function, you add the @Composable annotation to the function name.",
            modifier = modifier.padding(16.dp),
            textAlign = TextAlign.Justify
        )
    }
}

@Composable
fun BodyImage(msg: String, modifier: Modifier = Modifier) {
    val image = painterResource(R.drawable.bg_compose_background)
    Column(
        verticalArrangement = Arrangement.SpaceAround, modifier = modifier
    ) {
        Image(
            painter = image,
            contentDescription = null,
        )
        TextBody(msg)
    }
}