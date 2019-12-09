import day01.Day1
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day1Test {

    @Test
    fun solvePartOne() {
        assertEquals("3232358", Day1.solvePartOne())
    }

    @Test
    fun solvePartTwo() {
        assertEquals("4845669", Day1.solvePartTwo())
    }
}