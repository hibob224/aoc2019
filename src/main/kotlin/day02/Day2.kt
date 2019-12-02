package day02

import java.io.File

fun main(args: Array<String>) {
    println("Part one: ${Day2.solvePartOne()}")
    println("Parse two: ${Day2.solvePartTwo()}")
}

object Day2 {

    private val input = File("src/main/kotlin/day02/input.txt").readText().split(",").map { it.toInt() }

    fun solvePartOne(): String {
        val partOneInput = ArrayList(input)
        partOneInput[1] = 12
        partOneInput[2] = 2
        return runProgram(partOneInput).toString()
    }

    fun solvePartTwo(): String {
        var noun = 0
        var verb = -1
        var output = 0

        do {
            if (verb == 99) {
                verb = 0
                noun++
            } else {
                verb++
            }

            val input = ArrayList(input)
            input[1] = noun
            input[2] = verb
            output = runProgram(input)
        } while (output != 19690720)

        return  (100 * noun + verb).toString()
    }

    private fun runProgram(input: ArrayList<Int>): Int {
        var position = 0
        do {
            val opcode = input[position]
            val inp1 = input[position.inc()]
            val inp2 = input[position.inc().inc()]
            val out = input[position.inc().inc().inc()]

            if (inp1 > input.lastIndex || inp2 > input.lastIndex || out > input.lastIndex) {
                return -1 // Exception
            }

            when (opcode) {
                1 -> input[out] = input[inp1] + input[inp2]
                2 -> input[out] = input[inp1] * input[inp2]
                99 -> return input[0]
                else -> return -1 // Exception
            }

            position += 4
        } while (true)
    }
}