package kotlinBasicsUnit5import kotlinx.coroutines.*
import kotlin.system.*

fun main() {
    val time1 = measureTimeMillis {
        runBlocking {
            println("Weather forecast")
            printForecast()
            printTemperature()
        }
    }
    println("Execution time: ${time1 / 1000.0} seconds")
    val time2 = measureTimeMillis {
        runBlocking {
            println("Weather forecast")
            launch {
                printForecast()
            }
            launch {
                printTemperature()
            }
            println("Out of the launch statement")
        }
    }
    println("Execution time: ${time2 / 1000.0} seconds")
    val time3 = measureTimeMillis {
        runBlocking {
            println("Weather forecast")
            val forecast: Deferred<String> = async {
                printForecast()
            }
            val temperature: Deferred<String> = async {
                printTemperature()
            }
            println("${forecast.await()} ${temperature.await()}")
            println("Have a good day!")
        }
    }
    println("Execution time: ${time3 / 1000.0} seconds")
    runBlocking {
        print(getWeatherReport())
    }
}

suspend fun printForecast(): String {
    delay(1000)
    return ("Sunny")
}

suspend fun printTemperature(): String {
    delay(1000)
    return ("30\u00b0C")
}

//suspend fun getWeatherReport() = coroutineScope {
//    val forecast = async { printForecast() }
//    val temperature = async { printTemperature() }
//    "${forecast.await()} \n ${temperature.await()}"
//}
