package se.aoc.twentytwo

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day2Test {
    val day = Day2()

    val input = Day2::class.java.getResource("/twentytwo/day2test.txt").readText()

    @Test
    fun part1() {
        val list = day.createList(input)
        assertEquals(15, day.part1(list))
    }

    @Test
    fun part2() {
        val list = day.createList(input)
        assertEquals(12, day.part2(list))
    }
}

