package se.aoc.twentyfive

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class Day4Test {
    val day = Day4()
    val input = Day4::class.java.getResource("/twentyfive/test4.txt").readText()

    @Test
    fun part1() {
        val list = day.createList(input)
        println(list)
        assertEquals(13, day.part1(list))
    }

    @Test
    fun part2() {
        val list = day.createList(input)
        assertEquals(43, day.part2(list))
    }
}

