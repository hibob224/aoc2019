package day08

import utils.then
import java.io.File

fun main() {
    println("Part one: ${Day8.solvePartOne()}")
    println("Part two: ${Day8.solvePartTwo()}")
}

object Day8 {

    private const val wid = 25
    private const val hei = 6

    private fun parseInput(): List<String> = File("src/main/kotlin/day08/input.txt").readLines()

    fun solvePartOne(): String {
        val data = parseInput().first().chunked(wid * hei).map { it.groupingBy { it }.eachCount() }
        return data.minBy {
            it.getOrDefault('0', 0)
        }!!.let { it.getOrDefault('1', 0) * it.getOrDefault('2', 0) }.toString()
    }

    fun solvePartTwo(): String {
        val data = parseInput().first().chunked(wid * hei)
        val image = (0..data.first().length.dec()).map { '2' }.toMutableList() // Start with fully transparent image

        data.forEach {
            it.forEachIndexed { index, c ->
                if (image[index] == '2') {
                    image[index] = c // If pixel is currently transparent, replace it with the current layers pixel (which may be colour)
                }
            }
        }

        // Print out so we can read the answer
        image.chunked(wid).forEach { row ->
            row.forEach {
                print((it == '1') then '#' ?: ' ')
            }
            println()
        }

        // Returning the image list for testing purposes
        return image.joinToString("")
    }
}