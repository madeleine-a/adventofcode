package se.aoc.twentythree

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day7Test {
    val day = Day7()
    val input = Day1::class.java.getResource("/twentythree/day7test.txt").readText()
val s = """
    23456 100
    55544 100
    88888 100
    77778 100
""".trimIndent()
    @Test
    fun part1() {
        val list = day.createList(input)
        assertEquals(6440, day.part1(list))
    }

    @Test
    fun part2() {
        val list = day.createList(input)
        assertEquals(5905, day.part2(list))
    }
}

