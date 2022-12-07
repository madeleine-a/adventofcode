package se.aoc.twentytwo

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class Day7Test {
    val day = Day7()
    val input = Day7::class.java.getResource("/twentytwo/day7test.txt").readText()
    @Test
    fun part1() {
        day.createList(input)
        assertEquals(95437, day.part1())
    }

    @Test
    fun part2() {
        day.createList(input)
        assertEquals(24933642, day.part2())
    }

}

