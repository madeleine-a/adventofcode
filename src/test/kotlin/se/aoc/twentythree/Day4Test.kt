package se.aoc.twentythree

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day4Test {
    val day = Day4()
    val input = Day4::class.java.getResource("/twentythree/day4test.txt").readText()

    @Test
    fun part1() {
        val list = day.createList(input)
        assertEquals(13, day.part1(list))
    }

    @Test
    fun part2() {
        val list = day.createList(input)
        assertEquals(30, day.part2(list))
    }
}

