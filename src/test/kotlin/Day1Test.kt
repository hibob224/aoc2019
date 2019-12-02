package day01

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day1Test {

    @Test
    fun solvePartOne() {
        assertEquals(Day1.solvePartOne(), "3232358")
    }

    @Test
    fun solvePartTwo() {
        assertEquals(Day1.solvePartTwo(), "4845669")
    }
}