package bookshelf

import android.app.Application
import bookshelf.data.AppContainer
import bookshelf.data.DefaultAppContainer

class BookshelfInfoApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}