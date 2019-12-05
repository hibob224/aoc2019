package day05

import utils.then
import java.io.File

fun main() {
    println("Part one: ${Day5.solvePartOne()}")
    println("Parse two: ${Day5.solvePartTwo()}")
}

object Day5 {

    private fun parseInput(): List<Int> = File("src/main/kotlin/day05/input.txt")
        .readLines()[0]
        .split(",")
        .map { it.toInt() }

    fun solvePartOne(): String = doOperation(1)

    fun solvePartTwo(): String = doOperation(5)

    private fun doOperation(input: Int): String {
        val data = parseInput().toMutableList()
        val outputs = mutableListOf<Int>()
        var position = 0

        do {
            val opcode = data[position].toString().padStart(5, '0')
            val parsedOpcode = opcode.substring(opcode.lastIndex.dec()).toInt()
            val modes = mutableListOf(
                opcode[0].toString().toInt(),
                opcode[1].toString().toInt(),
                opcode[2].toString().toInt())

            when (parsedOpcode) {
                1 -> {
                    val out = data[position.inc().inc().inc()]
                    val inp1 = if (modes[2] == 0) {
                        data[data[position.inc()]]
                    } else {
                        data[position.inc()]
                    }
                    val inp2 = if (modes[1] == 0) {
                        data[data[position.inc().inc()]]
                    } else {
                        data[position.inc().inc()]
                    }
                    data[out] = inp1 + inp2
                    position += 4
                }
                2 -> {
                    val out = data[position.inc().inc().inc()]
                    val inp1 = if (modes[2] == 0) {
                        data[data[position.inc()]]
                    } else {
                        data[position.inc()]
                    }
                    val inp2 = if (modes[1] == 0) {
                        data[data[position.inc().inc()]]
                    } else {
                        data[position.inc().inc()]
                    }
                    data[out] = inp1 * inp2
                    position += 4
                }
                3 -> {
                    val out = data[position.inc()]
                    data[out] = input
                    position += 2
                }
                4 -> {
                    val out = if (modes[2] == 0) {
                        data[data[position.inc()]]
                    } else {
                        data[position.inc()]
                    }
                    outputs.add(out)
                    position += 2
                }
                5 -> {
                    val inp1 = if (modes[2] == 0) {
                        data[data[position.inc()]]
                    } else {
                        data[position.inc()]
                    }
                    val inp2 = if (modes[1] == 0) {
                        data[data[position.inc().inc()]]
                    } else {
                        data[position.inc().inc()]
                    }
                    if (inp1 != 0) {
                        position = inp2
                    } else {
                        position += 3
                    }
                }
                6 -> {
                    val inp1 = if (modes[2] == 0) {
                        data[data[position.inc()]]
                    } else {
                        data[position.inc()]
                    }
                    val inp2 = if (modes[1] == 0) {
                        data[data[position.inc().inc()]]
                    } else {
                        data[position.inc().inc()]
                    }
                    if (inp1 == 0) {
                        position = inp2
                    } else {
                        position += 3
                    }
                }
                7 -> {
                    val out = data[position.inc().inc().inc()]
                    val inp1 = if (modes[2] == 0) {
                        data[data[position.inc()]]
                    } else {
                        data[position.inc()]
                    }
                    val inp2 = if (modes[1] == 0) {
                        data[data[position.inc().inc()]]
                    } else {
                        data[position.inc().inc()]
                    }
                    data[out] = (inp1 < inp2) then 1 ?: 0
                    position += 4
                }
                8 -> {
                    val out = data[position.inc().inc().inc()]
                    val inp1 = if (modes[2] == 0) {
                        data[data[position.inc()]]
                    } else {
                        data[position.inc()]
                    }
                    val inp2 = if (modes[1] == 0) {
                        data[data[position.inc().inc()]]
                    } else {
                        data[position.inc().inc()]
                    }
                    data[out] = (inp1 == inp2) then 1 ?: 0
                    position += 4
                }
                99 -> return outputs.last().toString()
                else -> throw IllegalStateException("Unknown opcode $opcode")
            }
        } while (true)
    }
}