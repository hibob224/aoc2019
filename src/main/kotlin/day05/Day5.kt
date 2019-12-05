package day05

import utils.then
import java.io.File

fun main() {
//    Day5.solvePartOne()
    Day5.solvePartTwo()
//    println("Part one: ${Day5.solvePartOne()}")
//    println("Parse two: ${Day5.solvePartTwo()}")
}

object Day5 {

    private fun parseInput(): List<Int> = File("src/main/kotlin/day05/input.txt")
        .readLines()[0]
        .split(",")
        .map { it.toInt() }

    //7988899
    fun solvePartOne(): String {
        val systemId = 1
        val data = parseInput().toMutableList()
        val outputs = mutableListOf<Int>()
        var position = 0

        do {
            val opcode = data[position].toString()
            var parsedOpcode = -1
            val modes = mutableListOf(0, 0, 0)

            if (opcode.length <= 2) {
                parsedOpcode = opcode.toInt()
            } else {
                parsedOpcode = opcode.substring(opcode.lastIndex.dec()).toInt()
                when (opcode.length) {
                    5 -> {
                        modes[0] = opcode[0].toString().toInt()
                        modes[1] = opcode[1].toString().toInt()
                        modes[2] = opcode[2].toString().toInt()
                    }
                    4 -> {
                        modes[1] = opcode[0].toString().toInt()
                        modes[2] = opcode[1].toString().toInt()
                    }
                    3 -> {
                        modes[2] = opcode[0].toString().toInt()
                    }
                }
            }

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
                    data[out] = systemId
                    position += 2
                }
                4 -> {
                    val out = if (modes[2] == 0) {
                        data[data[position.inc()]]
                    } else {
                        data[position.inc()]
                    }
                    println(out)
                    position += 2
                }
                99 -> return ""
                else -> throw IllegalStateException("Unknown opcode $opcode")
            }
        } while (true)
    }

    //<14217502
    fun solvePartTwo(): String {
        val systemId = 5
        val data = parseInput().toMutableList()
//        val data = "3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99".split(",").map { it.toInt() }.toMutableList()
        var position = 0

        do {
            val opcode = data[position].toString()
            var parsedOpcode = -1
            val modes = mutableListOf(0, 0, 0)

            if (opcode.length <= 2) {
                parsedOpcode = opcode.toInt()
            } else {
                parsedOpcode = opcode.substring(opcode.lastIndex.dec()).toInt()
                when (opcode.length) {
                    5 -> {
                        modes[0] = opcode[0].toString().toInt()
                        modes[1] = opcode[1].toString().toInt()
                        modes[2] = opcode[2].toString().toInt()
                    }
                    4 -> {
                        modes[1] = opcode[0].toString().toInt()
                        modes[2] = opcode[1].toString().toInt()
                    }
                    3 -> {
                        modes[2] = opcode[0].toString().toInt()
                    }
                }
            }

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
                    data[out] = systemId
                    position += 2
                }
                4 -> {
                    val out = if (modes[2] == 0) {
                        data[data[position.inc()]]
                    } else {
                        data[position.inc()]
                    }
                    println(out)
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
                99 -> return ""
                else -> throw IllegalStateException("Unknown opcode $opcode")
            }
        } while (true)
    }
}