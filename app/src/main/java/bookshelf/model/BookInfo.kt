package bookshelf.model


data class Book(
    val id: String,
    val description: String,
    val volumeInfo: VolumeInfo,
    val saleInfo: SaleInfo
) {
    // Notes: This works too...
    fun getPrice(): String {
        if (saleInfo.listPrice == null) {
            return ""
        }
        return "${saleInfo.listPrice.amount} ${saleInfo.listPrice.currency}"
    }

}

data class VolumeInfo(
    val title: String,
    val subtitle: String,
    val description: String,
    val imageLinks: ImageLinks? = null,
    val authors: List<String>,
    val publisher: String,
    val publishedDate: String,
) {
    val allAuthorsx: String
        get() = allAuthors()

    fun allAuthors(): String {
        var x = ""
        for (author in authors) {
            x += "$author, "
        }
        return x.trimEnd(',', ' ')
    }
}

data class BookResponse(
    val items: List<BookInfo>?
)

data class BookInfo(
    val title: String,
    val authors: List<String> = emptyList(),
    val imageLinks: ImageLinks? = null,
    val description: String = "",
    val publisher: String = "",
    val publishedDate: String = "",
    val averageRating: Float? = null,
    val ratingsCount: Int? = null,
    val industryIdentifiers: List<IndustryIdentifier>? = null,
    val canonicalVolumeLink: String = "",
    val language: String = "",
    val buyLink: String = ""
)

data class ImageLinks(
    val smallThumbnail: String,
    val thumbnail: String,
) {
    val httpsThumbnail: String
        get() = thumbnail.replace("http", "https")
}

data class IndustryIdentifier(
    val type: String?,
    val identifier: String?
)

data class SaleInfo(
    val country: String,
    val isEbook: Boolean,
    val listPrice: ListPrice?
) {
    // Notes: This works...
    val getPrice2: String
        get() = "${listPrice?.amount ?: "N/A"} ${listPrice?.currency ?: "N/A"}"
}

data class ListPrice(
    val amount: Float?,
    val currency: String? = ""
)


