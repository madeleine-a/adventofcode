package se.aoc.twentyone

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day14Test {
    val str = "NNCB"
    val day = Day14()

    fun getInput(fileName: String): List<String> {
        val input = Day14::class.java.getResource("/twentyone/$fileName").readText()
        return input.split("\r\n")
    }

    @Test
    fun run() {
        day.run()
    }

    @Test
    fun part1() {
        day.str = str
        assertEquals(1588, day.part1(getInput("day14test.txt")))
    }

    @Test
    fun part2() {
        day.str = str
        assertEquals(2188189693529, day.part2(getInput("day14test.txt")))
    }
}