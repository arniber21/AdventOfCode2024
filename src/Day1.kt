import java.io.BufferedReader
import java.io.FileReader
import kotlin.math.abs

fun main() {
    // Load data
    val input: BufferedReader = BufferedReader(FileReader("input/day1input.txt"))
    val first = mutableListOf<Int>()
    val second = mutableListOf<Int>()
    while(input.ready()) {
        val line = input.readLine() ?: break
        val firstNumber = line.split("   ")[0].toInt()
        val secondNumber = line.split("   ")[1].toInt()

        first.add(firstNumber)
        second.add(secondNumber)
    }

    val res = solve(first, second)
    val sim = similarity(first, second)
    print(sim)
}

fun solve(first: List<Int>, second: List<Int>): Int {
    // Sort both lists
    val firstSorted = first.sorted()
    val secondSorted = second.sorted()

    var result = 0
    var i = 0
    while(i < firstSorted.size) {
        result += abs(firstSorted[i] - secondSorted[i])
        i++
    }
    return result
}

fun similarity(first: List<Int>, second: List<Int>): Int {
    // Build frequency for second
    val frequencyMap = mutableMapOf<Int, Int>()
    second.forEach { element -> frequencyMap.put(element, frequencyMap.getOrPut(element) { -> 0 } + 1) }

    println(frequencyMap)

    // For each in first
    var similarity = 0
    first.forEach { element -> similarity += element * frequencyMap.getOrElse(element) { -> 0} }

    return similarity
}