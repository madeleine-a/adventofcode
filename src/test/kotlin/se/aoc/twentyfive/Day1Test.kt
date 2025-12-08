package se.aoc.twentyfive

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day1Test {
    val day = Day1()
    val input = Day1::class.java.getResource("/twentyfive/test1.txt").readText()

    @Test
    fun part1() {
        val list = day.createList(input)
        assertEquals(3, day.part1(list))
    }

    @Test
    fun part2() {
        val list = day.createList(input)
        assertEquals(6, day.part2(list))
    }
}

