package busschedule.data

import androidx.room.Dao
import androidx.room.Query
import busschedule.data.BusSchedule
import kotlinx.coroutines.flow.Flow

@Dao
interface ScheduleDao {
    @Query(
        """
            SELECT * FROM schedule ORDER BY arrival_time ASC
        """
    )
    fun getAll(): Flow<List<BusSchedule>>

    @Query(
        """
            SELECT * FROM schedule WHERE stop_name = :stop ORDER BY arrival_time ASC
        """
    )
    fun getStop(stop: String): Flow<List<BusSchedule>>
}