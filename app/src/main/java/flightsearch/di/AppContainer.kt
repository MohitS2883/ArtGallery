package flightsearch.di

import android.content.Context
import flightsearch.data.FlightSearchDatabase
import flightsearch.data.FlightSearchRepository
import flightsearch.data.FlightSearchRepositoryImpl

interface AppContainer {
    val flightSearchRepository: FlightSearchRepository
}

class AppDataContainer(private val context: Context) : AppContainer {


    override val flightSearchRepository: FlightSearchRepository by lazy{
        FlightSearchRepositoryImpl(
            FlightSearchDatabase.getDatabase(context).airportDao(),
            FlightSearchDatabase.getDatabase(context).favoriteDao())
    }
}