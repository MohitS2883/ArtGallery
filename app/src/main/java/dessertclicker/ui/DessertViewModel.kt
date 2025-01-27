package dessertclicker.ui

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import com.example.artgallery.R
import dessertclicker.model.Dessert
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DessertViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(BakeryUiState())
    val uiState: StateFlow<BakeryUiState> = _uiState.asStateFlow()


    private fun determineDessertToShow(
        desserts: List<Dessert>,
    ) {
        for (dessert in desserts) {
            if (_uiState.value.dessertsSold >= dessert.startProductionAmount) {
                _uiState.update { currentState ->
                    currentState.copy(
                        dessertToShow = dessert,
                        currentDessertImageId = dessert.imageId,
                        currentDessertPrice = dessert.price
                    )
                }
            } else {
                break
            }
        }
    }

    fun dessertClickOperation(dessertList: List<Dessert>) {
        _uiState.value.revenue += _uiState.value.currentDessertPrice
        _uiState.value.dessertsSold += 1
        determineDessertToShow(dessertList)
        determineDessertToShow(dessertList)
    }

    fun shareSoldDessertsInformation(intentContext: Context, dessertsSold: Int, revenue: Int) {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(
                Intent.EXTRA_TEXT,
                intentContext.getString(R.string.share_text, dessertsSold, revenue)
            )
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)

        try {
            ContextCompat.startActivity(intentContext, shareIntent, null)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(
                intentContext,
                intentContext.getString(R.string.sharing_not_available),
                Toast.LENGTH_LONG
            ).show()
        }
    }
}