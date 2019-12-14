import day12.Day12
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day12Test {

    @Test
    fun solvePartOne() {
        assertEquals("8362", Day12.solvePartOne())
    }

    @Test
    fun solvePartTwo() {
        assertEquals("478373365921244", Day12.solvePartTwo())
    }
}