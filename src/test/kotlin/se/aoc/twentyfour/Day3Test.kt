package se.aoc.twentyfour

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day3Test {
    val day = Day3()
    val input = Day3::class.java.getResource("/twentyfour/day3test.txt").readText()

    @Test
    fun part1() {
        val list = day.createList(input)
        assertEquals(2, day.part1(list))
    }

    @Test
    fun part2() {
        val list = day.createList(input)
        assertEquals(4, day.part2(list))
    }
}

