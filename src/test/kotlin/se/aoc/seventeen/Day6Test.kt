package se.aoc.seventeen

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day6Test {
    private val d = Day6()
    val list = listOf(0, 2, 7, 0)

    @Test
    fun part1() {
        d.runLoop(list)
        assertEquals(5,d.part1())
    }

    @Test
    fun part2() {
        d.runLoop(list)
        assertEquals(4,d.part2())
    }


}