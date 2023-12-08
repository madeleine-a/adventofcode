package se.aoc.twentythree

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day6Test {
    val day = Day6()
    val input = """Time:      7  15   30
    Distance:  9  40  200"""

    @Test
    fun part1() {
        val list = day.createList(input)
        assertEquals(288L, day.part1(list))
    }

    @Test
    fun part2() {
        assertEquals(71503L, day.part2(mapOf(71530L to 940200L)))
    }
}

