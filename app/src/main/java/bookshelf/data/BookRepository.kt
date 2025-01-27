package bookshelf.data

import bookshelf.model.Book
import bookshelf.model.QueryResponse
import bookshelf.network.BookApiService

interface BookRepository {
    suspend fun getInfo(query: String): List<Book>
}


class BookInfoRepository(
    private val bookApiService: BookApiService
) : BookRepository {
    override suspend fun getInfo(query: String): List<Book> {
        return try {
            val res = bookApiService.getInfo(query)
            if (res.isSuccessful) {
                QueryResponse.items ?: emptyList()
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}


