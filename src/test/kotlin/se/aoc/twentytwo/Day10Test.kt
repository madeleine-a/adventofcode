package se.aoc.twentytwo

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class Day10Test {
    val day = Day10()
    val input = Day10::class.java.getResource("/twentytwo/day10test.txt").readText()
    @Test
    fun part1() {
        day.createList(input)
        assertEquals(-1, day.part1())
    }

    @Test
    fun part1_1() {
        day.createList(Day10::class.java.getResource("/twentytwo/day10test2.txt").readText())
        assertEquals(13140, day.part1())
    }

    @Test
    fun part2() {
        day.createList(input)
        assertEquals(1, day.part2())
    }

    @Test
    fun part2_2() {
        day.createList(Day10::class.java.getResource("/twentytwo/day10test2.txt").readText())
        assertEquals(36, day.part2())
    }

}

