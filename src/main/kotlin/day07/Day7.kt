package day07

import day05.Day5
import utils.then
import java.io.File
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.LinkedBlockingQueue
import kotlin.math.max

fun main() {
    println("Part one: ${Day7.solvePartOne()}")
    println("Part two: ${Day7.solvePartTwo()}")
}

object Day7 {

    private fun parseInput(): List<Int> = File("src/main/kotlin/day07/input.txt")
        .readLines()
        .first()
        .split(",")
        .map { it.toInt() }

    fun solvePartOne(): String {
        var highestSignal = 0

        permutations(0..4).forEach {
            val aOutput = Day5.doOperation(listOf(it.a), parseInput().toMutableList())
            val bOutput = Day5.doOperation(listOf(it.b, aOutput), parseInput().toMutableList())
            val cOutput = Day5.doOperation(listOf(it.c, bOutput), parseInput().toMutableList())
            val dOutput = Day5.doOperation(listOf(it.d, cOutput), parseInput().toMutableList())
            val eOutput = Day5.doOperation(listOf(it.e, dOutput), parseInput().toMutableList())
            highestSignal = max(highestSignal, eOutput)
        }

        return highestSignal.toString()
    }

    fun solvePartTwo(): String {
        var highestSignal = 0
        val range = 5..9

        permutations(5..9).forEach { perm ->
            val ampE = Amp().also {
                it.inputs.add(perm.e)
            }
            val ampD = Amp().also {
                it.inputs.add(perm.d)
                it.nextAmp = ampE
            }
            val ampC = Amp().also {
                it.inputs.add(perm.c)
                it.nextAmp = ampD
            }
            val ampB = Amp().also {
                it.inputs.add(perm.b)
                it.nextAmp = ampC
            }
            val ampA = Amp().also {
                it.inputs.addAll(listOf(perm.a, 0)) // Amp A gets an extra starting input of 0 as per instructions
                it.nextAmp = ampB
            }
            ampE.nextAmp = ampA

            Executors.newCachedThreadPool().also {
                it.submit(ampA)
                it.submit(ampB)
                it.submit(ampC)
                it.submit(ampD)
                highestSignal = max(highestSignal, it.submit(ampE).get())
            }
        }

        return highestSignal.toString()
    }

    private fun permutations(range: IntRange): List<Permutation> {
        val permutations = mutableListOf<Permutation>()
        for (a in range) {
            for (b in range) {
                for (c in range) {
                    for (d in range) {
                        for (e in range) {
                            if (setOf(a, b, c, d, e).size != 5) {
                                // Don't want any duplicate inputs
                                continue
                            }
                            permutations.add(Permutation(a, b, c, d, e))
                        }
                    }
                }
            }
        }
        return permutations
    }

    data class Permutation(
        val a: Int,
        val b: Int,
        val c: Int,
        val d: Int,
        val e: Int)

    data class Amp(
        val memory: MutableList<Int> = parseInput().toMutableList(),
        var latestOutput: Int = -1) : Callable<Int> {
        lateinit var nextAmp: Amp // Next amp in line which will receive output from this amp
        val inputs = LinkedBlockingQueue<Int>()

        override fun call(): Int = doOperation(this)
    }

    fun doOperation(amp: Amp): Int {
        val data = amp.memory
        var position = 0

        do {
            val opcode = data[position].toString().padStart(5, '0')
            val parsedOpcode = opcode.substring(opcode.lastIndex.dec()).toInt()
            val modes = mutableListOf(
                opcode[0].toString().toInt(),
                opcode[1].toString().toInt(),
                opcode[2].toString().toInt()
            )

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
                    data[out] = amp.inputs.take() // Will block here until we get a value from previous amp
                    position += 2
                }
                4 -> {
                    val out = if (modes[2] == 0) {
                        data[data[position.inc()]]
                    } else {
                        data[position.inc()]
                    }
                    amp.latestOutput = out
                    amp.nextAmp.inputs.add(out)
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
                99 -> {
                    return amp.latestOutput
                }
                else -> throw IllegalStateException("Unknown opcode $opcode")
            }
        } while (true)
    }
}