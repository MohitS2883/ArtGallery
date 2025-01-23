package kotlinBasicsUnit3

fun main() {
    val planets1 = arrayOf("Mercury", "Venus", "Earth", "Mars")
    val planets2 = arrayOf("Jupiter", "Saturn", "Uranus", "Neptune")
    val solarSystem = planets1 + planets2
    println(planets1[0])
    println(planets2[2])
    println(solarSystem[5])
    println("The planets of our solar system are:")
    for (i in solarSystem) {
        println(i)
    }
    val solarSystemWithList = listOf<String>(
        "Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune"
    )
    println("Solar System with List")
    for (planet in solarSystemWithList) {
        println(planet)
    }
    println(solarSystemWithList.size)
    println(solarSystemWithList[2])
    println(solarSystemWithList[0])
    println(solarSystemWithList.indexOf("Earth"))
    println(solarSystemWithList.indexOf("Pluto"))
    val solarSystemWithMutableList = mutableListOf<String>(
        "Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune"
    )
    val fMoon = "Future Moon"
    solarSystemWithMutableList.add("Pluto")
    solarSystemWithMutableList.add(3, "Theia")
    solarSystemWithMutableList[3] = fMoon
    println(solarSystemWithMutableList[3])
    println(solarSystemWithMutableList[9])
    solarSystemWithMutableList.removeAt(9)
    solarSystemWithMutableList.remove(fMoon)
    println(fMoon in solarSystemWithMutableList)

    val solarSystemWithMutableSet = mutableSetOf<String>(
        "Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune"
    )
    println(solarSystemWithMutableSet.size)
    solarSystemWithMutableSet.add("Pluto")
    println(solarSystemWithMutableSet.size)
    println(solarSystemWithMutableSet.contains("Pluto"))
    solarSystemWithMutableSet.add("Pluto")
    println(solarSystemWithMutableSet.size)
    solarSystemWithMutableSet.remove("Pluto")
    println("After removing")
    println(solarSystemWithMutableSet.size)
    println(solarSystemWithMutableSet.contains("Pluto"))
    println("Map operations")
    val moonMap = mutableMapOf<String, Int>(
        "Mercury" to 0,
        "Venus" to 0,
        "Earth" to 1,
        "Mars" to 2,
        "Jupiter" to 79,
        "Saturn" to 82,
        "Uranus" to 27,
        "Neptune" to 14
    )
    println(moonMap.size)
    moonMap["Pluto"] = 5
    println(moonMap)
    println(moonMap["Pluto"])
    println(moonMap["Theia"])
    moonMap.remove("Pluto")
    println(moonMap.size)
    moonMap["Jupiter"] = 78
    println(moonMap["Jupiter"])

}