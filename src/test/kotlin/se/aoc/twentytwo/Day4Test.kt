package se.aoc.twentytwo

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day4Test {
    val day = Day4()

    val input = Day4::class.java.getResource("/twentytwo/day4test.txt").readText()

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

