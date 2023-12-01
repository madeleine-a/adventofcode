package se.aoc.twentythree

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day1Test {
    val day = Day1()
    val input = Day1::class.java.getResource("/twentythree/day1_test.txt").readText()

    @Test
    fun part1() {
        val list = day.createList(input)
        assertEquals(142, day.part1(list))
    }

    @Test
    fun part2() {
        val list = listOf(
            "sevenine",
            "two1nine",
            "eightwothree",
            "abcone2threexyz",
            "xtwone3four",
            "4nineeightseven2",
            "zoneight234",
            "7pqrstsixteen"
        )
        assertEquals(360, day.part2(list))
    }
}

