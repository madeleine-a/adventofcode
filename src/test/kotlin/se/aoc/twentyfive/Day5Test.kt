package se.aoc.twentyfive

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


internal class Day5Test {
    val day = Day5()
    val input = Day5::class.java.getResource("/twentyfive/test5.txt").readText()

    @Test
    fun part1() {
        val list = day.createList(input)
        assertEquals(3, day.part1(list.first, list.second))
    }

    @Test
    fun part2() {
        val list = day.createList(input)
        assertEquals(14, day.part2(list.first))
    }

    @Test
    fun part2_again() {
        val list = listOf(
            (5L to 8L),
            (1L to 2L),
            (2L to 21L),
            (20L to 77L),
            (78L to 100L),
        )
        assertEquals(100, day.part2(list))

    }

    @Test
    fun chooseCounting(){
        val range = (1L .. 1000L)
        println(range.count())
        println(range.last - range.first + 1)
    }
}

