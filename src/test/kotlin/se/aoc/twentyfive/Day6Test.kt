package se.aoc.twentyfive

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day6Test {
    val day = Day6()
    val input = Day6::class.java.getResource("/twentyfive/test6.txt").readText()

    @Test
    fun part1() {
        val list = day.createList(input)
        assertEquals(4277556L, day.part1(list))
    }

    @Test
    fun part2() {
        val list = day.createList(input)
        assertEquals(3263827L, day.part2(list))
    }
}

