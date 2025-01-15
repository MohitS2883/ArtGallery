package com.example.artgallery.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artgallery.R
import com.example.artgallery.ui.theme.ArtGalleryTheme

@Preview(showBackground = true)
@Composable
fun BusinessCardPreview() {
    ArtGalleryTheme {
        MainBody()
    }
}
val iconMap: Map<String, ImageVector> = mapOf(
    "Phone" to Icons.Filled.Call,
    "Mail" to Icons.Filled.MailOutline,
    "Social" to Icons.Filled.Person
)

@Composable
fun CommunicationRow(content: String, icon: String, modifier: Modifier = Modifier) {
    val iconName = iconMap[icon]
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom,
        modifier = modifier.padding(2.dp)
    ) {
        if (iconName != null) {
            Icon(
                imageVector = iconName, contentDescription = icon, tint = Color.Blue
            )
        }
        Text(
            text = content
        )
    }
}

@Composable
fun CommunicationContent(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(top = 4.dp, bottom = 4.dp)
    ) {
        CommunicationRow(content = "9353620448", icon = "Phone")
        CommunicationRow(content = "svenkatmohit@gmail.com", icon = "Mail")
        CommunicationRow(content = "shirtpant_tie", icon = "Social")
    }
}

@Composable
fun ImageContent(name: String, designation: String, modifier: Modifier = Modifier) {
    val image = painterResource(R.drawable.ic_task_completed)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
        Text(
            text = name,
            fontSize = 36.sp,
            modifier = Modifier
                .padding(8.dp)
                .align(alignment = Alignment.CenterHorizontally)
        )
        Text(
            text = designation,
            fontSize = 36.sp,
            modifier = Modifier
                .padding(8.dp)
                .align(alignment = Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun MainBody(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        ImageContent(
            name = "S Venkat Mohit",
            designation = "Intern",
            modifier = Modifier
                .align(Alignment.Center)
        )
        CommunicationContent(
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}
