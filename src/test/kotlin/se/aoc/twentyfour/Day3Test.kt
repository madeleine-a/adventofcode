package se.aoc.twentyfour

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day3Test {
    val day = Day3()
    val input = Day3::class.java.getResource("/twentyfour/day3test.txt").readText()

    @Test
    fun parseString(){
        val str = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))"
        val seq = day.parseString(str)
        seq.iterator().forEachRemaining {
            println(it.value)
        }
    }

    @Test
    fun parseStringPart2(){
        val str = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"
        val seq = day.parseString(str)
        seq.iterator().forEachRemaining {
            println(it.value)
        }
    }

    @Test
    fun part1() {
        val list = day.parseString(input)
        assertEquals(161, day.part1(list))
    }

    @Test
    fun part2() {
        val input = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"
        val list = day.parseString(input)
        assertEquals(48, day.part2(list))
    }
}

