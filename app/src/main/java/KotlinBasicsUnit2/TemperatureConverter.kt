package kotlinBasicsUnit3

fun main() {
    // Fill in the code.
    val celToFahr: (Double) -> Double = { celcius ->
        ((9.0/5.0) * celcius + 32)
    }
    val kelToCel: (Double) -> Double = { kelvin ->
        kelvin - 273.15
    }
    val fahrToKel: (Double) -> Double = { fahrenheit ->
        ((5.0/9.0) * (fahrenheit - 32)) + 273.15
    }
    printFinalTemperature(27.0,"Celcius","Fahrenheit",celToFahr)
    printFinalTemperature(350.0,"Kelvin","Celcius",kelToCel)
    printFinalTemperature(10.0,"Fahrenheit","Kelvin",fahrToKel)
}


fun printFinalTemperature(
    initialMeasurement: Double,
    initialUnit: String,
    finalUnit: String,
    conversionFormula: (Double) -> Double
) {
    val finalMeasurement = String.format("%.2f", conversionFormula(initialMeasurement)) // two decimal places
    println("$initialMeasurement degrees $initialUnit is $finalMeasurement degrees $finalUnit.")
}