package se.aoc.twentyone

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import se.aoc.twentyone.day9.Day9

internal class Day9Test {
    val day = Day9()
    val input = Day9Test::class.java.getResource("/twentyone/day9test.txt").readText()


    @Test
    fun run() {
        day.run()
    }

    @Test
    fun part1() {
        assertEquals(15, day.part1(input))
    }

    @Test
    fun part2() {
        assertEquals(1134L, day.part2(input))
    }
}