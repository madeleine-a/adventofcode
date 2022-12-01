package se.aoc.twentytwo

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import se.aoc.twentyone.Day4

internal class Day1Test{
    val day = Day1()

    val input = Day4::class.java.getResource("/twentytwo/day1_test.txt").readText()
    @Test
    fun part1(){
        val list = day.createList(input)
        assertEquals(24000, day.part1(list))
    }

    @Test
    fun part2(){
        val list = day.createList(input)
        assertEquals(45000, day.part2(list))
    }
}

