package se.aoc.seventeen

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day2Test {
    private val strList = listOf("5 9 2 8", "9 4 7 3", "3 8 6 5")
    val input = strList.map { it.replace("\\s".toRegex(), "").toList().map { c -> c.digitToInt() } }.toList()

    val d = Day2()

    @Test
    fun part1() {
        assertEquals(18, d.part1(input))
    }

    @Test
    fun part2() {
        assertEquals(9, d.part2(input))
    }
}