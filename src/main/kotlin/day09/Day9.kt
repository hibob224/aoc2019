package day09

import IntCodeComputer
import day05.Day5
import java.io.File

fun main() {
    println("Part one: ${Day9.solvePartOne()}")
    println("Part two: ${Day9.solvePartTwo()}")
}

object Day9 {

    private fun parseInput(): List<Long> = File("src/main/kotlin/day09/input.txt")
        .readLines()[0]
        .split(",")
        .map { it.toLong() }

    fun solvePartOne(): String = solve(1)

    fun solvePartTwo(): String = solve(2)

    private fun solve(input: Long): String = IntCodeComputer(parseInput().toLongArray()).also {
        it.addInput(input)
    }.call().toString()
}