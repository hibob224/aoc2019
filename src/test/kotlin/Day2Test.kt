import day02.Day2
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day2Test {

    @Test
    fun solvePartOne() {
        assertEquals("3765464", Day2.solvePartOne())
    }

    @Test
    fun solvePartTwo() {
        assertEquals("7610", Day2.solvePartTwo())
    }
}