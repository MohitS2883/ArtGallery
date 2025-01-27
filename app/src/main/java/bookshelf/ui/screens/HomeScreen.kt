package bookshelf.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.artgallery.R
import bookshelf.model.Book

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookApp(
    modifier: Modifier = Modifier
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    var search = remember { mutableStateOf("") }
    val bookViewModel: BookViewModel = viewModel(factory = BookViewModel.Companion.Factory)

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.app_name),
                        style = MaterialTheme.typography.headlineMedium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                },
                scrollBehavior = scrollBehavior,
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF964B00),
                    titleContentColor = Color.White,
                    scrolledContainerColor = Color(0xFF964B00),
                )
            )
        },
    ) { padding ->
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(modifier = Modifier.padding(
                padding
            )) {
                TextField(
                    value = search.value,
                    onValueChange = { search.value = it },
                    label = { Text("Enter the search term") },
                    placeholder = { Text("Book Name") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Search
                    ),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            bookViewModel.getBookInfo(search.value)
                        }
                    ),
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                )
                HomeScreen(
                    bookUiState = bookViewModel.bookUiState.value,
                    retryAction = { query -> bookViewModel.getBookInfo(query) },
                    contentPadding = PaddingValues(1.dp),
                    query = search.value
                )
            }
        }
    }

}


@Composable
fun BookCard(photoName: String, modifier: Modifier = Modifier) {
    AsyncImage(
        model = ImageRequest.Builder(context = LocalContext.current)
            .data(photoName)
            .crossfade(true)
            .build(),
        contentDescription = null,
        contentScale = ContentScale.FillWidth,
        error = painterResource(id = R.drawable.ic_broken_image),
        placeholder = painterResource(id = R.drawable.loading_img),
        modifier = modifier.fillMaxWidth()
    )
}


@Composable
fun HomeScreen(
    bookUiState: BookUiState,
    retryAction: (String) -> Unit,
    query: String,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    modifier: Modifier = Modifier
) {
    when (bookUiState) {
        is BookUiState.Loading -> LoadingScreen(modifier = Modifier.fillMaxSize())
        is BookUiState.Success -> BooksGrid(
            photos = bookUiState.books,
            modifier = modifier
                .padding(
                    start = 16.dp,
                    top = 16.dp,
                    end = 16.dp
                ),
            contentPadding = contentPadding
        )

        is BookUiState.Error -> ErrorScreen(
            retryAction = { retryAction(query) },
            modifier = modifier.fillMaxSize()
        )
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BooksGrid(
    photos: List<Book>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        modifier = modifier
            .imePadding(),
        contentPadding = contentPadding
    ) {
        items(
            items = photos,
            key = { photo -> photo.id },
        ) { photo ->
            BookCard(
                photoName = photo.volumeInfo.imageLinks?.httpsThumbnail
                    ?: "https://example.com/default_image.png",
                modifier = Modifier
                    .padding(2.dp)
                    .fillMaxSize()
                    .aspectRatio(1f)

            )
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