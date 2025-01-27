package busschedule

import android.app.Application
import busschedule.data.BusStopDatabase


class BusApplication : Application() {
    val database: BusStopDatabase by lazy { BusStopDatabase.getDatabase(this) }

}