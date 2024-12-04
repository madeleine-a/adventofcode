package se.aoc.twentyfour

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day4Test {
    val day = Day4()
    val input = Day4::class.java.getResource("/twentyfour/day4test.txt").readText()


    @Test
    fun parsePoints(){
        val result = day.parseToPoints(input)
        result.iterator().forEachRemaining {
            println(it)
        }
    }


    @Test
    fun part1() {
        val list = day.parseToPoints(input)
        assertEquals(18L, day.part1(list))
    }

    @Test
    fun part2() {
        val list = day.parseToPoints(input)
        assertEquals(9L, day.part2(list))
    }
}

