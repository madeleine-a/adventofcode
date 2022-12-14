package se.aoc.twentytwo

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day14Test {
    val day = Day14()

    val input = Day14::class.java.getResource("/twentytwo/day14test.txt").readText()

    @Test
    fun part1() {
         day.createList(input)
        assertEquals(24, day.part1())
    }

    @Test
    fun part2() {
        day.createList(input)
        day.haveFloor = true
        assertEquals(93, day.part2())
    }
}

