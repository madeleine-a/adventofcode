package se.aoc.eighteen

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class Day1Test {
private val d = Day1()

    @ParameterizedTest
    @CsvSource(value = ["+1,-2,+3,+1:3", "+1, +1, +1:3","+1, +1, -2:0", "-1, -2, -3:-6"],
        delimiter = ':')
    fun part1(str: String, result: Int) {
        val list = str.split(",").map { it.trim() }.map { it.toInt() }
        assertEquals(result, d.part1(list))
    }


    @ParameterizedTest
    @CsvSource(value = ["+1,-2,+3,+1:2", "+1,-1:0",
        "+3, +3, +4, -2, -4:10", "-6, +3, +8, +5, -6:5", "+7, +7, -2, -7, -4:14"],
        delimiter = ':')
    fun part2(str: String, result: Int) {
        val list = str.split(",").map { it.trim() }.map { it.toInt() }
        assertEquals(result, d.part2(list))
    }

    @Test
    fun run(){
        d.run()
    }
}