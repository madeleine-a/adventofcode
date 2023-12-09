package se.aoc.twentythree

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import splitStrOfNumbersToList

internal class Day9Test {
    val day = Day9()
    val input = Day9::class.java.getResource("/twentythree/day9test.txt").readText()

    @Test
    fun part1() {
        val list = day.createList(input)
        assertEquals(114L, day.part1(list))
    }

    @Test
    fun part1_1()
    {
        val list = setOf("17 15 17 31 75 193 478 1098 2316 4488 8014 13206 20019 27555 33193 31168 10612 -45996 -160136 -339076 -513992".splitStrOfNumbersToList())
        day.part1(list)
    }

    @Test
    fun part2() {
        val list = day.createList(input)
        assertEquals(2L, day.part2(list))
    }
}

