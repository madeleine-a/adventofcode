package se.aoc.twentyone

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

import se.aoc.twentyone.day11.Day11

internal class Day11Test {
    val day = Day11()
    val input = Day11::class.java.getResource("/twentyone/day11test.txt").readText()
    val list = input.split("\r\n")

    @Test
    fun run() {
        day.run()
    }

    @Test
    fun part1() {
        assertEquals(1656, day.part1(list))

    }

    @Test
    fun part2() {
        assertEquals(195, day.part2(list))
    }
}