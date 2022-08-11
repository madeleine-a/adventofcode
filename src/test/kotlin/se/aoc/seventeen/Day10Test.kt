package se.aoc.seventeen

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class Day10Test{
    val numbers = (0..4).toList()
    val input = "3, 4, 1, 5".split(",").map { it.trim().toInt() }

    val d = Day10()
    @Test
    fun part1(){
        assertEquals(12, d.part1(input, numbers))
    }

    @ParameterizedTest
    @CsvSource("1,2,3; 3efbe78a8d82f29979031a4aa0b16a9d",
        "AoC 2017;33efeb34ea91902bb2f59c9920caa6cd", "1,2,4; 63960835bcdc130f0b66d7ff4f6a5a8e", delimiter = ';')
    fun part2(str: String, result: String){
        assertEquals(result, d.part2((0..255).toList(), str))
    }

    @Test
    fun part2EmptyString(){
        assertEquals("a2582a3a0e66e6e86e3812dcb672a272", d.part2((0..255).toList(), ""))
    }
}