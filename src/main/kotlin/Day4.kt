fun String?.isEmptyOrNull(): Boolean {
    val isNull = this == null
    val isEmpty = !isNull && this!!.isNotEmpty()

    return isNull || isEmpty
}

fun main(args: Array<String>) {
    val s = "abc"
    println(s as? Int)    // null
    println(s as Int?)    // exception
}