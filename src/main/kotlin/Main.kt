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
    println(isValidIdentifier("name"))   // true
    println(isValidIdentifier("_name"))  // true
    println(isValidIdentifier("_12"))    // true
    println(isValidIdentifier(""))       // false
    println(isValidIdentifier("012"))    // false
    println(isValidIdentifier("no$"))    // false
}