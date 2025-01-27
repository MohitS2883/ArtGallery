package bookshelf.ui.screens

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import bookshelf.BookshelfInfoApplication
import bookshelf.data.BookRepository
import bookshelf.model.Book
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException

sealed interface BookUiState {
    data class Success(val books: List<Book>) : BookUiState
    object Error : BookUiState
    object Loading : BookUiState
}

class BookViewModel(private val bookRepository: BookRepository) : ViewModel() {
    var bookUiState = mutableStateOf<BookUiState>(BookUiState.Loading)
        private set
    init {
        getBookInfo("")
    }
    fun getBookInfo(query: String) {
        viewModelScope.launch {
            bookUiState.value = BookUiState.Loading
            bookUiState.value = try {
                BookUiState.Success(
                    bookRepository.getInfo(query)
                )
            } catch (e: IOException) {
                BookUiState.Error
            } catch (e: HttpException) {
                BookUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as BookshelfInfoApplication)
                val bookRepository = application.container.bookRepository
                BookViewModel(bookRepository = bookRepository)
            }
        }
    }
}