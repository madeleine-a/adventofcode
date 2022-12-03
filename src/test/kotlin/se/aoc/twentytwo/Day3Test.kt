package se.aoc.twentytwo

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day3Test {
    val day = Day3()

    val input = Day3::class.java.getResource("/twentytwo/day3test.txt").readText()

    @Test
    fun part1() {
        val list = day.createList(input)
        assertEquals(157, day.part1(list))
    }

    @Test
    fun part2() {
        val list = day.createList(input)
        assertEquals(70, day.part2(list))
    }
}

