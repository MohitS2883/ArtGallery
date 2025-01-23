package kotlinBasicsUnit3

//
//class FillInTheBlankQuestion(
//    val questionText: String,
//    val answer: String,
//    val difficulty: String
//)
//class TrueOrFalseQuestion(
//    val questionText: String,
//    val answer: Boolean,
//    val difficulty: String
//)
//
//class NumericQuestion(
//    val questionText: String,
//    val answer: Int,
//    val difficulty: String
//)

data class Question<T>(
    val questionText: String, val answer: T, val difficulty: Difficulty
)

enum class Difficulty {
    EASY, MEDIUM, HARD
}

object StudentProgress {
    var total: Int = 10
    var answered: Int = 3
}


interface ProgressPrintable {
    val progressText: String
    fun printProgressBar()
}

class Quiz : ProgressPrintable {
    override val progressText: String
        get() = "$answered of $total answered"

    override fun printProgressBar() {
        repeat(answered) { print("*") }
        repeat(total - answered) { print("-") }
        println()
        println(progressText)
    }

    val question1 = Question<String>("Quoth the raven ___", "nevermore", Difficulty.MEDIUM)
    val question2 = Question<Boolean>("The sky is green. True or false", false, Difficulty.EASY)
    val question3 =
        Question<Int>("How many days are there between full moons?", 28, Difficulty.HARD)

    companion object StudentProgress {
        var total: Int = 10
        var answered: Int = 3

    }

    fun printQuiz() {
        question1.let {
            println(it.questionText)
            println(it.answer)
            println(it.difficulty)
        }
        println()
        question2.let {
            println(it.questionText)
            println(it.answer)
            println(it.difficulty)
        }
        println()
        question3.let {
            println(it.questionText)
            println(it.answer)
            println(it.difficulty)
        }
        println()
    }
}


fun main() {
    val q1 = Question<String>("What is your name?", "Mohit", Difficulty.EASY)
    val q2 = Question<Int>("2+2=?", 2, Difficulty.MEDIUM)
    val q3 = Question<Boolean>("I am from Bangalore, True or False", true, Difficulty.HARD)
    println(q1.questionText)
    println(q1.answer)
    println(q1.difficulty)
    println(q2.questionText)
    println(q2.answer)
    println(q2.difficulty)
    println(q3.questionText)
    println(q3.answer)
    println(q3.difficulty)
    println("${StudentProgress.total} ${StudentProgress.answered}")
    println(Quiz.answered)
    println(Quiz.total)
    println(Quiz().progressText)
    Quiz().printProgressBar()
    val quiz = Quiz()
    quiz.printQuiz()
    Quiz().apply {
        printQuiz()
    }
}
