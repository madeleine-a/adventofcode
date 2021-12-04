package se.aoc.twentyone

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day4Test {
    val day = Day4()
    @Test
    fun run() {
        day.run()
    }

    @Test
    fun part1() {
        val input = Day4::class.java.getResource("/twentyone/day4test.txt").readText()
        assertEquals(4512, day.part1(input))
    }

    @Test
    fun part2() {
        val input = Day4::class.java.getResource("/twentyone/day4test.txt").readText()
        assertEquals(1924, day.part2(input))
    }
}