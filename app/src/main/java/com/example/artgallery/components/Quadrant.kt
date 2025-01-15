package com.example.artgallery.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.artgallery.R
import com.example.artgallery.ui.theme.ArtGalleryTheme

@Preview(showBackground = true)
@Composable
fun QuadrantPreview() {
    ArtGalleryTheme {
        AppBody()
    }
}

@Composable
fun AppBody(modifier: Modifier = Modifier) {
    Column(
        modifier.fillMaxWidth()
    ) {
        Row(Modifier.weight(1f)) {
            IndividualCard(
                title = stringResource(R.string.titleString),
                msg = stringResource(R.string.msgString),
                bgColor = Color(0xFFEADDFF),
                modifier = Modifier.weight(1f)
            )
            IndividualCard(
                title = stringResource(R.string.titleString),
                msg = stringResource(R.string.msgString),
                bgColor = Color(0xFFD4E4BC),
                modifier = Modifier.weight(1f)
            )
        }
        Row(Modifier.weight(1f)) {
            IndividualCard(
                title = stringResource(R.string.titleString),
                msg = stringResource(R.string.msgString),
                bgColor = Color(0xFF1D8A99),
                modifier = Modifier.weight(1f)
            )
            IndividualCard(
                title = stringResource(R.string.titleString),
                msg = stringResource(R.string.msgString),
                bgColor = Color(0xFFBC4749),
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun IndividualCard(title: String, msg: String, bgColor: Color, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(bgColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = msg,
            textAlign = TextAlign.Justify
        )
    }
}