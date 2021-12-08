package se.aoc.twentyone

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day8Test {
    val day = Day8()
    val input = Day8Test::class.java.getResource("/twentyone/day8test.txt").readText()

    @Test
    fun run() {
        day.run()
    }

    @Test
    fun calculatePattern(){
        day.init("acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | cdfeb fcadb cdfeb cdbaf")
        day.calculatePattern(day.list.first().first.split(" "))
    }
    @Test
    fun part1() {
        assertEquals(26, day.part1(input))
    }

    @Test
    fun part2() {
        assertEquals(61229, day.part2(input))
    }
}