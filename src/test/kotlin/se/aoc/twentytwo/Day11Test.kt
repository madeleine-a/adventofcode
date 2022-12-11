package se.aoc.twentytwo

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.math.BigInteger

internal class Day11Test {
    val day = Day11()
    val input = Day11::class.java.getResource("/twentytwo/day11test.txt").readText()
    @Test
    fun part1() {
        day.createList(input)
        assertEquals(10605L, day.part1())
    }

    @Test
    fun part2() {
        day.createList(input)
        assertEquals(2713310158L, day.part2())
    }


}

