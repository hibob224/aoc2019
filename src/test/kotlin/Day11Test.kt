import day11.Day11
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day11Test {

    @Test
    fun solvePartOne() {
        assertEquals("2478", Day11.solvePartOne())
    }

    @Test
    fun solvePartTwo() {
        assertEquals(".##....##..#.#..#.#..#..##.##..###..#.#..##....##..#.#..#.#...#.##.#....##..###.............#.#....##.##.##...##...##..##....##..#.#..###....#.#...........##..###..###..##....##...#.##.#...###..##...##...#.#..#.#..#.#...#.##.##..##....##..##.##..#..", Day11.solvePartTwo())
    }
}