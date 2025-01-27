package com.example.amphibian.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.artgallery.R
import com.example.amphibian.network.AmphibianInfo
import com.example.amphibian.ui.theme.AmphibianTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AmphibiansApp() {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        stringResource(R.string.app_name),
                        style = MaterialTheme.typography.headlineMedium,
                    )
                },
                scrollBehavior = scrollBehavior
            )
        }
    ) {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxSize()
        ) {
            val amphibianViewModel: AmphibianViewModel =
                viewModel(factory = AmphibianViewModel.Factory)
            HomeScreen(
                amphibianUiState = amphibianViewModel.amphibianUiState.value,
                retryAction = amphibianViewModel::getAmphibianInfo,
                contentPadding = it,
            )
        }
    }
}

@Composable
fun HomeScreen(
    amphibianUiState: AmphibianUiState,
    retryAction: () -> Unit,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    modifier: Modifier = Modifier
) {
    when (amphibianUiState) {
        is AmphibianUiState.Loading -> LoadingScreen(modifier = Modifier.fillMaxSize())
        is AmphibianUiState.Success -> CardList(
            information = amphibianUiState.info,
            modifier = modifier
                .padding(
                    start = 16.dp,
                    top = 16.dp,
                    end = 16.dp
                ),
            contentPadding = contentPadding
        )

        is AmphibianUiState.Error -> ErrorScreen(
            retryAction,
            modifier = modifier.fillMaxSize()
        )
    }
}


@Composable
fun AmphibianInfoCard(
    info: AmphibianInfo, modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = modifier,
    ){
        Column(
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = info.name + " (" + info.type + ")",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(
                    18.dp
                )
            )
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(info.imgSrc)
                    .crossfade(true)
                    .build(),
                contentDescription = info.imgSrc,
                contentScale = ContentScale.FillWidth,
                error = painterResource(id = R.drawable.ic_broken_image),
                placeholder = painterResource(id = R.drawable.loading_img),
                modifier = Modifier.fillMaxWidth()
            )
            InfoPart(
                info = info, modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            )

        }
    }
}

@Composable
fun InfoPart(info: AmphibianInfo, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
    ) {
        Text(
            text = info.description,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Justify,
            modifier = Modifier.padding(start = 5.dp)
        )
    }
}

@Composable
fun ErrorScreen(
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
        )
        Text(text = "Loading Failed", modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text("Retry")
        }

    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = "Loading"
    )
}


@Composable
fun CardList(
    information: List<AmphibianInfo>,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    modifier: Modifier
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(24.dp),
        contentPadding = contentPadding,
        modifier = modifier,

        ) {
        items(
            items = information,
            key = { info ->
                info.name
            }) { individualInfo ->
            AmphibianInfoCard(
                info = individualInfo
            )
        }
    }
}

@Composable
fun AmphibianInfoCardStatic(
    info: AmphibianInfo, modifier: Modifier = Modifier,
) {
    Card {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = modifier
        ) {
            Text(
                text = info.name + " (" + info.type + ")",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(
                    horizontal = 8.dp,
                    vertical = 4.dp,
                )
            )
            Image(
                painter = painterResource(R.drawable.great_basin_spadefoot),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
            )
            InfoPart(
                info = info, modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth()
            )

        }
    }
}

@Preview
@Composable
fun AmphibianCardPreview() {
    AmphibianTheme {
        AmphibianInfoCardStatic(
            AmphibianInfo(
                name = "Great Basin Spadefoot",
                type = "Toad",
                description = "This toad spends most of its life underground due to the arid desert conditions in which it lives. Spadefoot toads earn the name because of their hind legs which are wedged to aid in digging. They are typically grey, green, or brown with dark spots.",
                imgSrc = R.drawable.example.toString()
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    AmphibianTheme {
        LoadingScreen(modifier = Modifier)
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    AmphibianTheme {
        ErrorScreen(
            retryAction = {
                print("retry")
            },
            modifier = Modifier
        )
    }
}


//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun TopAppBar(scrollBehavior: TopAppBarScrollBehavior, modifier: Modifier = Modifier) {
//    CenterAlignedTopAppBar(
//        scrollBehavior = scrollBehavior, title = {
//            Text(
//                text = stringResource(R.string.app_name),
//                style = MaterialTheme.typography.headlineSmall,
//            )
//        }, modifier = modifier
//    )
//}
