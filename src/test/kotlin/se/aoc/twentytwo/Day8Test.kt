package se.aoc.twentytwo

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class Day8Test {
    val day = Day8()
    val input = Day8::class.java.getResource("/twentytwo/day8test.txt").readText()
    @Test
    fun part1() {
        day.createList(input)
        assertEquals(21, day.part1())
    }

    @Test
    fun part2() {
        day.createList(input)
        assertEquals(8, day.part2())
    }

}

