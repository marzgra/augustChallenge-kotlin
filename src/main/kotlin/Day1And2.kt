import Color.*

fun listFunctions() {
    val list = listOf("Java")
//    list.add("Kotlin") // cannot edit read-only collection

    val mutableList = mutableListOf("Java")
    mutableList.add("Kotlin")
}

fun getMax(a: Int, b: Int): Int {
    return if (a > b) a else b
}

fun getMaxFunc(a: Int, b: Int) = if (a > b) a else b


fun isValidIdentifier(s: String): Boolean {
    if (s.isBlank() || s[0] in '0'..'9') return false

    fun isValidChar(ch: Char): Boolean =
        ch in 'a'..'z' ||
                ch in 'A'..'Z' ||
                ch in '0'..'9' ||
                ch == '_'

    for (i in 1 until s.length) {
        if (!isValidChar(s[i])) return false
    }

    return true
}

fun main(args: Array<String>) {
    println(isValidIdentifier("012"))    // false
    println(isValidIdentifier("name"))   // true
    println(isValidIdentifier("_name"))  // true
    println(isValidIdentifier("_12"))    // true
    println(isValidIdentifier(""))       // false
    println(isValidIdentifier("no$"))    // false
}


enum class Color {
    BLUE, ORANGE, RED
}

fun updateWeatherJavaLikeVersion(degrees: Int) {
    val description: String
    val color: Color
    if (degrees < 10) {
        description = "cold"
        color = BLUE
    } else if (degrees < 25) {
        description = "warm"
        color = ORANGE
    } else {
        description = "hot"
        color = RED
    }
}

fun updateWeatherBetterVersion(degrees: Int) {
    val (description, color) =          // creates a Pair
        if (degrees < 10) {
            Pair("cold", BLUE)
        } else if (degrees < 25) {
            Pair("warm", ORANGE)
        } else {
            Pair("hot", RED)
        }
}


fun updateWeatherNoIfVersion(degrees: Int) {
    val (description, color) = when {
        degrees < 10 -> Pair("cold", BLUE)
        degrees < 25 -> Pair("warm", ORANGE)
        else -> Pair("hot", RED)
    }
}

fun updateWeatherWhenNoPairVersion(degrees: Int) {
    val (description, color) = when {
        degrees < 10 -> "cold" to BLUE
        degrees < 25 -> "warm" to ORANGE
        else -> "hot" to RED
    }
}