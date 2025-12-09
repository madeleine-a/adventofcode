package se.aoc.twentyfive

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class Day3Test {
    val day = Day3()
    val input = Day3::class.java.getResource("/twentyfive/test3.txt").readText()

    @Test
    fun part1() {
        val list = day.createList(input)
        println(list)
        assertEquals(357, day.part1(list))
    }

    @Test
    fun part2() {
        val list = day.createList(input)
        assertEquals(3121910778619, day.part2(list))
    }

    @Test
    fun getHighest(){
        val list = "234234234234278".toList()
        println(list)
        day.part2(listOf(list))
    }

}

