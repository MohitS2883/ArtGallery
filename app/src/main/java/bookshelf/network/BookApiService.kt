package bookshelf.network

import bookshelf.model.QueryResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BookApiService {
    @GET("volumes")
    suspend fun getInfo(@Query("q") query: String): Response<QueryResponse>
}