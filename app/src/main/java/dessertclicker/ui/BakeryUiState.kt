package dessertclicker.ui

import dessertclicker.data.Datasource.dessertList
import dessertclicker.model.Dessert

data class BakeryUiState(
    var revenue: Int = 0,
    var dessertsSold: Int = 0,
    var currentDessertIndex: Int = 0,
    var currentDessertPrice: Int = Dessert.price,
    var currentDessertImageId: Int = Dessert.imageId,
    var dessertToShow: Dessert = dessertList.first()
)