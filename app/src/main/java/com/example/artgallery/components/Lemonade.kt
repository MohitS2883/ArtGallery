package com.example.artgallery.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artgallery.R
import com.example.artgallery.ui.theme.ArtGalleryTheme

@Preview(showBackground = true)
@Composable
fun LemonadePreview() {
    ArtGalleryTheme {
        TreePage()
    }
}

@Composable
fun TreePage(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {

        TopBar(Modifier.background(Color.Yellow))
        ImageWithText(
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun ImageWithText(modifier: Modifier = Modifier) {
    val result = remember { mutableIntStateOf(1) }
    var noOfTimesToSqueeze = remember { mutableIntStateOf((2..4).random()) }
    val imageResource = when (result.intValue) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }
    val textMsg = when (result.intValue) {
        1 -> "Tap the lemon tree to select a lemon"
        2 -> "Keep tapping the lemon to squeeze it"
        3 -> "Tap the lemonade to drink it"
        else -> "Tap the empty glass to start again"
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        ButtonWithImage(result, noOfTimesToSqueeze, imageResource)
        Text(
            text = textMsg,
            fontSize = 20.sp,
            textAlign = TextAlign.Justify,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun ButtonWithImage(
    result: MutableIntState,
    noOfTimesToSqueeze: MutableIntState,
    imageResource: Int,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = {
            if (result.intValue < 3) {
                if (result.intValue == 2) {
                    if (noOfTimesToSqueeze.intValue > 0) {
                        noOfTimesToSqueeze.intValue -= 1
                    } else {
                        result.intValue += 1
                    }
                } else {
                    result.intValue += 1
                }
            } else {
                result.intValue = 0
                noOfTimesToSqueeze.intValue = (2..4).random()
            }
        }, modifier = modifier.padding(16.dp)
    ) {
        Image(
            painter = painterResource(imageResource),
            contentDescription = "Lemon Tree",
            modifier = Modifier.size(300.dp)
        )
    }
}

@Composable
fun TopBar(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                top = 12.dp, bottom = 12.dp
            )
            .background(Color.Yellow)
    ) {
        Text(
            text = "Lemonade", modifier = Modifier.align(Alignment.Center), fontSize = 50.sp
        )
    }
}