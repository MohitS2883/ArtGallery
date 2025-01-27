package flightsearch.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.artgallery.R
import flightsearch.data.Airport
import flightsearch.data.Favorite
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlightSearchScreen(
    viewModel: FlightSearchViewModel = viewModel(
        factory = FlightSearchViewModel.Companion.Factory
    )
) {
    val uiState: SearchUiState = viewModel.uiState.collectAsState().value
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val coroutineScope = rememberCoroutineScope()
    val text = remember { mutableStateOf("") }
    val active = remember { mutableStateOf(false) }

    HandleInitialSearchQuery(uiState, viewModel, text)

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { FlightSearchTopAppBar(scrollBehavior) }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            SearchBarSection(text, active, viewModel, uiState)
            FlightListSection(uiState, coroutineScope, viewModel)
        }
    }
}

@Composable
private fun HandleInitialSearchQuery(
    uiState: SearchUiState,
    viewModel: FlightSearchViewModel,
    text: MutableState<String>
) {
    if (uiState.searchQuery.isNotEmpty()) {
        text.value = uiState.searchQuery
        val currentAirportSearch = uiState.airportList.find { it.iataCode == uiState.searchQuery }
        currentAirportSearch?.let {
            viewModel.onSelectedAirportDepart(it)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FlightSearchTopAppBar(scrollBehavior: TopAppBarScrollBehavior) {
    TopAppBar(
        title = { Text(stringResource(R.string.app_name)) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF004896),
            titleContentColor = Color.White,
            scrolledContainerColor = Color(0xFF004896)
        ),
        scrollBehavior = scrollBehavior
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchBarSection(
    text: MutableState<String>,
    active: MutableState<Boolean>,
    viewModel: FlightSearchViewModel,
    uiState: SearchUiState
) {
    SearchBar(
        query = text.value,
        onQueryChange = {
            text.value = it
            viewModel.onSearchTextValueChange(text.value)
        },
        onSearch = { active.value = false },
        active = active.value,
        onActiveChange = {
            if (!active.value) {
                viewModel.onSearchTextValueChange(text.value)
            }
            active.value = it
        },
        placeholder = { Text(text = "Enter your query") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search icon"
            )
        },
        trailingIcon = {
            if (text.value.isNotEmpty() || active.value) {
                Icon(
                    modifier = Modifier.clickable {
                        if (text.value.isNotEmpty()) {
                            text.value = ""
                            viewModel.onCloseSearch()
                            active.value = false
                        } else {
                            active.value = false
                        }
                    },
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close icon"
                )
            }
        },
        colors = SearchBarDefaults.colors(
            containerColor = Color(0xFF509BC4),
            dividerColor = Color(0xFF86D2CF)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp)
    ) {
        AirportSearchResults(uiState, viewModel, active)
    }
}

@Composable
private fun AirportSearchResults(
    uiState: SearchUiState,
    viewModel: FlightSearchViewModel,
    active: MutableState<Boolean>
) {
    LazyColumn {
        items(items = uiState.filteredAirportList, key = { it.id }) { airport ->
            Row(
                modifier = Modifier
                    .padding(all = 14.dp)
                    .fillMaxWidth()
                    .clickable {
                        viewModel.onSearchTextValueChange(airport.iataCode)
                        viewModel.onSelectedAirportDepart(airport)
                        active.value = false
                    }
            ) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = null)
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = airport.iataCode)
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = airport.name)
            }
        }
    }
}

@Composable
private fun FlightListSection(
    uiState: SearchUiState,
    coroutineScope: CoroutineScope,
    viewModel: FlightSearchViewModel
) {
    LazyColumn(
        contentPadding = PaddingValues(5.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.padding(5.dp)
    ) {
        val headerText = if (uiState.selectedAirport == null) {
            if (uiState.favoriteList.isNotEmpty()) "Favorite routes" else null
        } else {
            "Flights from ${uiState.selectedAirport.iataCode}"
        }
        headerText?.let {
            item {
                Text(
                    text = it,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 5.dp)
                )
            }
        }
        if (uiState.selectedAirport == null) {
            items(items = uiState.favoriteList) { airport ->
                val depart = uiState.airportList.find { it.iataCode == airport.departureCode }!!
                val arrive = uiState.airportList.find { it.iataCode == airport.destinationCode }!!
                FlyCard(
                    depart = depart,
                    arrive = arrive,
                    favoriteList = uiState.favoriteList,
                    coroutineScope = coroutineScope,
                    viewModel = viewModel
                )
            }
        } else {
            items(
                items = uiState.airportList.filterNot { it == uiState.selectedAirport },
                key = { it.id }) { airport ->
                FlyCard(
                    depart = uiState.selectedAirport,
                    arrive = airport,
                    favoriteList = uiState.favoriteList,
                    coroutineScope = coroutineScope,
                    viewModel = viewModel
                )
            }
        }
    }
}


@Composable
fun FlyCard(
    depart: Airport,
    arrive: Airport,
    favoriteList: List<Favorite>,
    coroutineScope: CoroutineScope,
    viewModel: FlightSearchViewModel

) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            Column(Modifier.weight(1f)) {
                Text("DEPART", fontStyle = FontStyle.Italic)
                Spacer(modifier = Modifier.size(5.dp))
                Row {
                    Text(
                        depart.iataCode.uppercase(),
                        fontWeight = FontWeight.Bold,
                    )
                    Spacer(modifier = Modifier.size(5.dp))
                    Text(
                        depart.name,
                        fontSize = 15.sp,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }
                Spacer(modifier = Modifier.size(5.dp))
                Text("ARRIVE", fontStyle = FontStyle.Italic)
                Spacer(modifier = Modifier.size(5.dp))
                Row {
                    Text(
                        arrive.iataCode.uppercase(),
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.size(5.dp))
                    Text(
                        arrive.name,
                        fontSize = 15.sp,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }
            }
            val currentFavorite: Favorite? =
                favoriteList.find { favorite ->
                    favorite.departureCode == depart.iataCode && favorite.destinationCode == arrive.iataCode
                }
            IconButton(
                onClick = {
                    if (currentFavorite != null) {
                        coroutineScope.launch {
                            viewModel.run { deleteFavorite(currentFavorite) }
                        }
                    } else {
                        coroutineScope.launch {
                            viewModel.addFavorite(
                                depart = depart.iataCode,
                                arrive = arrive.iataCode
                            )
                        }
                    }
                },
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .weight(0.1f)
            ) {
                if (currentFavorite != null) {
                    Icon(
                        Icons.Default.Favorite, Icons.Default.Favorite.name
                    )
                } else {
                    Icon(
                        Icons.Default.FavoriteBorder,
                        Icons.Default.FavoriteBorder.name
                    )
                }
            }
        }
    }

}
