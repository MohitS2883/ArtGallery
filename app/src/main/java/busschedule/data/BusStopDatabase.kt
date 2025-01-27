package busschedule.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import busschedule.data.BusSchedule

@Database(entities = [BusSchedule::class], version = 3, exportSchema = false)
abstract class BusStopDatabase : RoomDatabase() {
    abstract fun busDao(): ScheduleDao

    companion object {
        @Volatile
        private var Instance: BusStopDatabase? = null

        fun getDatabase(context: Context): BusStopDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, BusStopDatabase::class.java, "bus_database")
                    .createFromAsset("database/bus_schedule.db")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}