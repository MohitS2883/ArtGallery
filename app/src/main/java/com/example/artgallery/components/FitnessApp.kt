package com.example.artgallery.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitnessapp.model.Exercise
import com.example.fitnessapp.model.ExerciseRepository
import com.example.fitnessapp.ui.theme.AppTheme
import com.example.artgallery.R

@Composable
private fun ExerciseCard(exercise: Exercise, modifier: Modifier = Modifier) {
    var expanded = remember(exercise) { mutableStateOf(false) }
    val color by animateColorAsState(
        targetValue = if (expanded.value) MaterialTheme.colorScheme.tertiaryContainer
        else MaterialTheme.colorScheme.primaryContainer,
    )
    Card(
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(16.dp),
        onClick = {
            expanded.value = !expanded.value
        },
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
                .background(color = color)
                .padding(
                    8.dp
                )
        ) {
            Text(
                text = stringResource(exercise.dayRes),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 6.dp, start = 16.dp, end = 5.dp
                    )
                    .align(alignment = Alignment.CenterHorizontally),
                style = MaterialTheme.typography.displayMedium,
            )
            Text(
                text = stringResource(exercise.titleRes),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .padding(
                        vertical = 8.dp, horizontal = 16.dp
                    ),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
            ImageUI(
                expanded = expanded, exercise = exercise
            )


        }
    }
}

@Composable
private fun ImageUI(
    expanded: State<Boolean>,
    exercise: Exercise,
) {
    if (expanded.value) {
        Image(
            painter = painterResource(exercise.imageRes),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )
        Text(
            text = stringResource(exercise.descriptionRes),
            modifier = Modifier.padding(
                vertical = 8.dp, horizontal = 16.dp
            ),

            )
    }

}

@Composable
private fun ExerciseList(modifier: Modifier = Modifier) {
    Scaffold(topBar = {
        TopCard()
    }) { name ->
        LazyColumn(
            modifier = modifier.fillMaxSize(), contentPadding = name
        ) {
            val exercises = ExerciseRepository.exercises
            items(items = exercises) {
                ExerciseCard(
                    exercise = it, modifier = Modifier.padding(
                        bottom = 8.dp, end = 16.dp, start = 16.dp
                    )
                )
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopCard() {
    CenterAlignedTopAppBar(title = {
        Text(
            text = "30 Days of WorkOut",
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier
        )
    })
}

@Preview(showBackground = true)
@Composable
private fun ExerciseCardPreview() {
    AppTheme {
        ExerciseCard(
            Exercise(
                dayRes = R.string.day_1,
                titleRes = R.string.title_1,
                imageRes = R.drawable.jumping_jacks,
                descriptionRes = R.string.description_1
            )
        )
    }
}