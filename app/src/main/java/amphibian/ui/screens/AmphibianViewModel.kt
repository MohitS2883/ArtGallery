package com.example.amphibian.ui.screens

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.amphibian.AmphibianInfoApplication
import com.example.amphibian.data.AmphibianRepository
import com.example.amphibian.network.AmphibianInfo
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException

sealed interface AmphibianUiState {
    data class Success(val info: List<AmphibianInfo>) : AmphibianUiState
    object Error : AmphibianUiState
    object Loading : AmphibianUiState
}

class AmphibianViewModel(private val amphibianRepository: AmphibianRepository) : ViewModel() {
    var amphibianUiState = mutableStateOf<AmphibianUiState>(AmphibianUiState.Loading)
        private set
    init {
        getAmphibianInfo()
    }
    fun getAmphibianInfo() {
        viewModelScope.launch {
            amphibianUiState.value = AmphibianUiState.Loading
            amphibianUiState.value = try {
                AmphibianUiState.Success(
                    amphibianRepository.getInfo()
                )
            } catch (e: IOException) {
                AmphibianUiState.Error
            } catch (e: HttpException) {
                AmphibianUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as AmphibianInfoApplication)
                val amphibianRepository = application.container.amphibianInfoRepository
                AmphibianViewModel(amphibianRepository = amphibianRepository)
            }
        }
    }
}

