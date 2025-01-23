package kotlinBasicsUnit3

data class Event(
    val title: String,
    val description: String? = null,
    val daypart: Daypart,
    val durationInMinutes: Int
)

val Event.durationOfEvent: String
    get() = if (this.durationInMinutes < 60) {
        "short"
    } else {
        "long"
    }

enum class Daypart {
    MORNING, AFTERNOON, EVENING
}

fun main() {
    val e = Event(
        title = "Study Kotlin",
        description = "Commit to studying Kotlin at least 15 minutes per day .",
        daypart = Daypart.EVENING,
        durationInMinutes = 15
    )
    val event1 = Event(
        title = "Wake up",
        description = "Time to get up",
        daypart = Daypart.MORNING,
        durationInMinutes = 0
    )
    val event2 = Event(title = "Eat breakfast", daypart = Daypart.MORNING, durationInMinutes = 15)
    val event3 =
        Event(title = "Learn about Kotlin", daypart = Daypart.AFTERNOON, durationInMinutes = 30)
    val event4 =
        Event(title = "Practice Compose", daypart = Daypart.AFTERNOON, durationInMinutes = 60)
    val event5 = Event(
        title = "Watch latest DevBytes video",
        daypart = Daypart.AFTERNOON,
        durationInMinutes = 10
    )
    val event6 = Event(
        title = "Check out latest Android Jetpack library",
        daypart = Daypart.EVENING,
        durationInMinutes = 45
    )
    val eventList = mutableListOf<Event>(
        event1, event2, event3, event4, event5, event6
    )
    for (event in eventList) {
        println(event)
    }
    val shotEventList = eventList.filter {
        it.durationInMinutes < 60
    }
    println("Events shorter than 60 mins")
    for (event in shotEventList) {
        println(event)
    }
    val groupedEvents = eventList.groupBy {
        it.daypart
    }
    for (event in groupedEvents) {
        println(event)
    }
    val groupedMorning = groupedEvents[Daypart.MORNING] ?: listOf()
    val groupedEvening = groupedEvents[Daypart.EVENING] ?: listOf()
    val groupedAfternoon = groupedEvents[Daypart.AFTERNOON] ?: listOf()
    println("Morning events")
    for (morningEvents in groupedMorning) {
        println(morningEvents)
    }
    println("Evening Events")
    for (eveningEvents in groupedEvening) {
        println(eveningEvents)
    }
    println("Afternoon events")
    for (afternoonEvents in groupedAfternoon) {
        println(afternoonEvents)
    }
    println("Last event of the day ${eventList.last().title}")
    println(eventList.last().durationOfEvent)
}