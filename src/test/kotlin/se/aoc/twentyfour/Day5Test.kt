package se.aoc.twentyfour

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day5Test {
    val day = Day5()
    val input = Day5::class.java.getResource("/twentyfour/day5test.txt").readText()

    @Test
    fun parseString(){
        val result = day.parseString(input)
        println(result.first.toList())
        result.second.forEach { println(it.toList()) }
    }



    @Test
    fun part1() {
        val list = day.parseString(input)
        assertEquals(161, day.part1(list.first, list.second))
    }

    @Test
    fun part2() {
        val list = day.parseString(input)
        assertEquals(161, day.part2(list.first, list.second))
    }
}

