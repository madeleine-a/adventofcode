package se.aoc.twentyone

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import se.aoc.twentyone.day11.Day11

internal class Day13Test {
    val day = Day13()
    val input = Day13::class.java.getResource("/twentyone/day13test.txt").readText()
    val list = input.split("\r\n")
    @Test
    fun run() {
        day.run()
    }

    @Test
    fun part1() {
        assertEquals(17, day.part1(list))
    }

    @Test
    fun part2() {
    }
}