class Day7 {
    fun isEven(i: Int): Boolean = i % 2 == 0

    val predicateReference = ::isEven

    val PredicateLamdba = {i: Int -> isEven(i)}
}