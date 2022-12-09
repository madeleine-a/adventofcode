package se.aoc.twentytwo

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class Day9Test {
    val day = Day9()
    val input = Day9::class.java.getResource("/twentytwo/day9test.txt").readText()
    @Test
    fun part1() {
        day.createList(input)
        assertEquals(13, day.part1())
    }

    @Test
    fun part2() {
        day.createList(input)
        assertEquals(1, day.part2())
    }

    @Test
    fun part2_2() {
        day.createList(Day9::class.java.getResource("/twentytwo/day9test2.txt").readText())
        assertEquals(36, day.part2())
    }

}

