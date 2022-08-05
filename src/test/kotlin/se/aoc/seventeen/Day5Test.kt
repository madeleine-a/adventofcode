package se.aoc.seventeen

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day5Test {
    private val d = Day5()
    val list = listOf(0, 3, 0, 1, -3)

    @Test
    fun part1(){
        assertEquals(5, d.part1(list))
    }

    @Test
    fun part2(){
        assertEquals(10, d.part2(list))
    }
}