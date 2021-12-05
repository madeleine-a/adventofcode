package se.aoc.twentyone

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day5Test {
    val day = Day5()

    @Test
    fun part1() {
        val input = Day5::class.java.getResource("/twentyone/day5test.txt").readText()
        assertEquals(5, day.part1(input))
    }

    @Test
    fun part2() {
        val input = Day5::class.java.getResource("/twentyone/day5test.txt").readText()
        assertEquals(12, day.part2(input))
    }
}