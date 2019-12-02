import day02.Day2
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day2Test {

    @Test
    fun solvePartOne() {
        assertEquals(Day2.solvePartOne(), "3765464")
    }

    @Test
    fun solvePartTwo() {
        assertEquals(Day2.solvePartTwo(), "7610")
    }
}