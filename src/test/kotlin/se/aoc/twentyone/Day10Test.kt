package se.aoc.twentyone

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day10Test {
    val day = Day10()
    private val input = Day10Test::class.java.getResource("/twentyone/day10test.txt").readText()
    val list = input.split("\r\n")

    @Test
    fun run() {
        day.run()
    }

    @Test
    fun part1() {
       assertEquals(26397, day.part1(list))
    }

    @Test
    fun part2() {
        assertEquals(288957, day.part2(list))
    }

    @Test
    fun count(){
        val str = "])}>"
        assertEquals(294, day.calcPartToStr(str))
    }
}