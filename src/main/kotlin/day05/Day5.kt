package day05

import IntCodeComputer
import utils.then
import java.io.File
import java.lang.IllegalArgumentException

fun main() {
    println("Part one: ${Day5.solvePartOne()}")
    println("Parse two: ${Day5.solvePartTwo()}")
}

object Day5 {

    private fun parseInput(): List<Long> = File("src/main/kotlin/day05/input.txt")
        .readLines()[0]
        .split(",")
        .map { it.toLong() }

    fun solvePartOne(): String = IntCodeComputer(parseInput().toLongArray()).also { it.addInput(1) }.call().toString()

    fun solvePartTwo(): String = IntCodeComputer(parseInput().toLongArray()).also { it.addInput(5) }.call().toString()

    fun doOperation(input: List<Long>, data: MutableList<Long> = parseInput().toMutableList()): Long {
        var inputPosition = 0
        val outputs = mutableListOf<Long>()
        var position = 0
        var relativeBase = 0

        do {
            val opcode = data[position].toString().padStart(5, '0')
            val parsedOpcode = opcode.substring(opcode.lastIndex.dec()).toInt()
            val modes = mutableListOf(
                opcode[0].toString().toInt(),
                opcode[1].toString().toInt(),
                opcode[2].toString().toInt()
            )

            val inp1 = when(modes[2]) {
                0 -> data.getOrElse(data.getOrElse(position.inc()) { 0L }.toInt()) { 0L }
                1 -> data.getOrElse(position.inc()) { 0L }
                2 -> data.getOrElse(data.getOrElse(position.inc()) { 0L }.toInt() + relativeBase) { 0L }
                else -> throw IllegalArgumentException("Fucked")
            }
            val inp2 = when(modes[1]) {
                0 -> data.getOrElse(data.getOrElse(position.inc().inc()) { 0L }.toInt()) { 0L }
                1 -> data.getOrElse(position.inc().inc()) { 0L }
                2 -> data.getOrElse(data.getOrElse(position.inc().inc()) { 0L }.toInt() + relativeBase) { 0L }
                else -> throw IllegalArgumentException("Fucked")
            }
            val inp3 = when(modes[0]) {
                0, 1 -> data.getOrElse(position.inc().inc().inc()) { 0L }
                2 -> data.getOrElse(position.inc().inc().inc()) { 0L } + relativeBase
                else -> throw IllegalArgumentException("Fucked")
            }.toInt()

            when (parsedOpcode) {
                1 -> {
                    data[inp3] = inp1 + inp2
                    position += 4
                }
                2 -> {
                    data[inp3] = inp1 * inp2
                    position += 4
                }
                3 -> {
                    val out = when(modes[2]) {
                        0, 1 -> data.getOrElse(position.inc()) { 0L }
                        2 -> data.getOrElse(position.inc()) { 0L } + relativeBase
                        else -> throw IllegalArgumentException("Fucked")
                    }.toInt()
                    data[out] = input.getOrElse(inputPosition) { 0L }
                    inputPosition++
                    position += 2
                }
                4 -> {
                    outputs.add(inp1)
                    println(inp1)
                    position += 2
                }
                5 -> {
                    if (inp1 != 0L) {
                        position = inp2.toInt()
                    } else {
                        position += 3
                    }
                }
                6 -> {
                    if (inp1 == 0L) {
                        position = inp2.toInt()
                    } else {
                        position += 3
                    }
                }
                7 -> {
                    data[inp3] = (inp1 < inp2) then 1L ?: 0L
                    position += 4
                }
                8 -> {
                    data[inp3] = (inp1 == inp2) then 1L ?: 0L
                    position += 4
                }
                9 -> {
                    relativeBase += inp1.toInt()
                    position += 2
                }
                99 -> return outputs.last()
                else -> throw IllegalStateException("Unknown opcode $opcode")
            }
        } while (true)
    }
}