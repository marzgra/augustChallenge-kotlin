data class Evaluation(val rightPosition: Int, val wrongPosition: Int)

fun evaluateGuess(secret: String, guess: String): Evaluation {
    var rightPosition = 0

    var guessCopy = guess
    var secretCopy = secret

    for ((index, c) in secretCopy.toList().withIndex()) {
        if (guess[index] == c) {
            guessCopy = guessCopy.replaceFirst(c.toString(), "")
            secretCopy = secretCopy.replaceFirst(c.toString(), "")
            rightPosition++
        }
    }

    var wrongPosition = 0
    for (c in secretCopy.toList()) {
        if (guessCopy.contains(c)) {
            guessCopy = guessCopy.replaceFirst(c.toString(), "")
            wrongPosition++
        }
    }

    return Evaluation(rightPosition, wrongPosition)
}