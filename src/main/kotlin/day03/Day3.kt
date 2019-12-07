package day03

import java.io.File
import kotlin.math.abs

fun main() {
    println("Part one: ${Day3.solvePartOne()}")
    println("Part two: ${Day3.solvePartTwo()}")
}

object Day3 {

    private val lineOne = mutableListOf(Point(0, 0))
    private val lineTwo = mutableListOf(Point(0, 0))

    init {
        val lineOneInput = parseInput()[0]
        val lineTwoInput = parseInput()[1]
        lineOneInput.forEach { lineOne.addAll(lineOne.last().move(it)) }
        lineTwoInput.forEach { lineTwo.addAll(lineTwo.last().move(it)) }
        lineOne.removeAt(0)
        lineTwo.removeAt(0)
    }

    private fun parseInput(): List<List<String>> = File("src/main/kotlin/day03/input.txt").readLines().map { it.split(",") }

    fun solvePartOne(): String = lineOne.intersect(lineTwo).map { it.manhattan() }.min().toString()

    fun solvePartTwo(): String = lineOne.intersect(lineTwo).map { intersect ->
        lineOne.first { intersect == it }.steps + lineTwo.first { intersect == it }.steps
    }.min().toString()

    data class Point(val x: Int, val y: Int) {

        var steps = 0

        fun move(movement: String): List<Point> {
            val direction = movement.first()
            val steps = movement.substring(1).toInt()

            return (1..steps).map { step ->
                when (direction) {
                    'R' -> copy(x = x + step)
                    'L' -> copy(x = x - step)
                    'U' -> copy(y = y - step)
                    'D' -> copy(y = y + step)
                    else -> throw IllegalArgumentException("Unknown movement type")
                }.also { it.steps += this.steps + step }
            }
        }

        fun manhattan(): Int = abs(x) + abs(y)

        override fun toString(): String {
            return "${super.toString()} - $steps"
        }
    }
}