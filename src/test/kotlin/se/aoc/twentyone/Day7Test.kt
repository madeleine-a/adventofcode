package se.aoc.twentyone

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day7Test {
    val day = Day7()
    val str = "16,1,2,0,4,2,7,1,2,14"

    @Test
    fun run() {
        day.run()
    }

    @Test
    fun part1() {
        val list = str.split(",").map { it.toInt() }
       assertEquals(37 ,  day.part1(list))
    }

    @Test
    fun part2() {
        val list = str.split(",").map { it.toInt() }
        assertEquals(168 ,  day.part2(list))
    }
}