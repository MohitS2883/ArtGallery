package kotlinBasicsUnit3

fun main(){
    val s1: Song = Song("A","B",2003,1000)
    s1.printInfo()
    println(s1.popular)
}


class Song(var title: String, var artist: String, var year: Int,var playCount:Int){
    val popular: Boolean
        get() = playCount >= 1000
    fun printInfo(){
        println("$title, performed by $artist, was released in $year")
    }
}