package dayN

import day03.Day3
import java.io.File

fun main() {
    println("Part one: ${Day3.solvePartOne()}")
    println("Parse two: ${Day3.solvePartTwo()}")
}

object DayN {

    fun parseInput(): List<String> = File("src/main/kotlin/dayN/input.txt").readLines()

    fun solvePartOne(): String {
        return ""
    }

    fun solvePartTwo(): String {
        return ""
    }
}