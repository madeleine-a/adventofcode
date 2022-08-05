package se.aoc.eighteen

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day5Test {
    private val day = Day5()

    @Test
    fun run() {
        day.run()
    }

    @Test
    fun part1() {
        assertEquals(10, day.part1("dabAcCaCBAcCcaDA"))
    }

    @Test
    fun part2() {
        assertEquals(4, day.part2("dabAcCaCBAcCcaDA"))
    }
}