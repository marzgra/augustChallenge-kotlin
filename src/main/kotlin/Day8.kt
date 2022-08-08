/**
 * Nice String
 * A string is nice if at least two of the following conditions are satisfied:
 *
 * It doesn't contain substrings bu, ba or be;
 * It contains at least three vowels (vowels are a, e, i, o and u);
 * It contains a double letter (at least two similar letters following one another), like b in "abba".
 */

fun String.isNice(): Boolean {
    val noBuBaBe: Boolean = !contains("bu") && !contains("ba") && !contains("be")
    var noOfVowels: Int = 0
    val vowels = listOf('a', 'e', 'i', 'o', 'u')

    for (i in indices) {
        if (vowels.contains(get(i)))
            noOfVowels++
    }

    var hasDoubles: Boolean = false
    for (i in 0 until length - 1) {
        val current = get(i)
        val next = get(i + 1)
        if (current == next) {
            hasDoubles = true
            break
        }
    }

    return (noBuBaBe && noOfVowels >= 3) ||
            (noBuBaBe && hasDoubles) ||
            (noOfVowels >= 3 && hasDoubles)
}

fun String.isNiceFun(): Boolean {
    val noBadSubstrings: Boolean = !contains("bu") && !contains("ba") && !contains("be")
    val hasThreeVowels = count { it == 'a' || it == 'e' || it == 'i' || it == 'o' || it == 'u' } > 3
    var hasDouble = false
    if (length > 1) {
        var prev: Char? = null
        for (ch in this) {
            if (ch == prev) {
                hasDouble = true
            }
            prev = ch
        }
    }

    var conditions = 0
    if(noBadSubstrings) conditions++
    if(hasThreeVowels) conditions++
    if(hasDouble) conditions++

    return conditions >= 2

}