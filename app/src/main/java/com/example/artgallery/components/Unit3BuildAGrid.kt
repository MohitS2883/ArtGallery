package com.example.artgallery.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artgallery.data.DataSource
import com.example.artgallery.model.Topic
import com.example.artgallery.ui.theme.ArtGalleryTheme
import com.example.artgallery.R


@Preview(showBackground = true)
@Composable
private fun TopicCardPreview() {
    ArtGalleryTheme {
        val course = Topic(
            stringResourceId = R.string.photography,
            noOfEnrolled = 321,
            imageResourceId = R.drawable.photography
        )
        TopicCard(course = course)
    }
}

@Preview(showBackground = true)
@Composable
private fun AppPreview() {
    ArtGalleryTheme {
        CourseApp()
    }
}

@Composable
private fun CourseApp() {
    val layoutDirection = LocalLayoutDirection.current
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(
                start = WindowInsets.safeDrawing.asPaddingValues()
                    .calculateStartPadding(layoutDirection),
                end = WindowInsets.safeDrawing.asPaddingValues()
                    .calculateEndPadding(layoutDirection),
            ),
    ) {
        CourseList(
            courseList = DataSource.topics,
        )
    }
}

@Composable
private fun CourseList(courseList: List<Topic>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
    ) {
        items(items = courseList.chunked(2)) { rowItems ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp),
                horizontalArrangement = Arrangement.spacedBy(2.5.dp)
            ) {
                rowItems.forEach { course ->
                    TopicCard(
                        course = course,
                        modifier = Modifier
                            .weight(1f)
                            .padding(2.dp)
                    )
                }
                if (rowItems.size < 2) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}


@Composable
private fun TopicCard(
    course: Topic, modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.card_color),
        ),
        modifier = modifier.height(68.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxHeight()
        ) {
            Image(
                painter = painterResource(course.imageResourceId),
                contentDescription = stringResource(course.stringResourceId),
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .width(68.dp)
                    .fillMaxHeight()
            )
            Column {
                Text(
                    text = LocalContext.current.getString(course.stringResourceId),
                    modifier = Modifier.padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 16.dp,
                        bottom = 4.dp
                    ),
                    fontSize = 14.sp
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.Top,
                    modifier = Modifier
                        .weight(0.5f)
                        .padding(
                            bottom = 2.dp
                        )
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_grain),
                        contentDescription = null,
                        modifier = Modifier.padding(
                            start = 16.dp,
                        )
                    )
                    Text(
                        text = course.noOfEnrolled.toString(), modifier = Modifier.padding(
                            start = 8.dp,
                        ), fontSize = 12.sp
                    )
                }
            }
        }
    }
}