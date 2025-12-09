package se.aoc.twentyfive

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class Day2Test {
    val day = Day2()
    val input = Day2::class.java.getResource("/twentyfive/test2.txt").readText()

    @Test
    fun part1() {
        val list = day.createList(input)
        println(list)
        assertEquals(1227775554, day.part1(list))
    }

    @Test
    fun part2() {
        val list = day.createList(input)
        assertEquals(4174379265, day.part2(list))
    }

    @ParameterizedTest
    @CsvSource("1212,true",
               "11,true",
               "12,false")
    fun testRepeated(str: String, result: Boolean) {
        assertEquals(result, day.hasValueRepeatablePatterns(str))
    }
}

