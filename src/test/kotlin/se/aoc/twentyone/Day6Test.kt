package se.aoc.twentyone

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day6Test {
    private val str = "3,4,3,1,2"
    val day = Day6()

    @Test
    fun run() {
        day.run()
    }

    @Test
    fun part1() {
        assertEquals(5934, day.part1(str))
    }

    @Test
    fun part2() {
        assertEquals(26984457539, day.part2(str))
    }
}