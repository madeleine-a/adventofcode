package se.aoc.twentyone

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day3Test {
    val day = Day3()

    val str = """00100
11110
10110
10111
10101
01111
00111
11100
10000
11001
00010
01010""".trimIndent()

    @Test
    fun run() {
        day.run()
    }

    @Test
    fun part1() {
        val list = str.split("""
""")
        assertEquals(198, day.part1(list))
    }

    @Test
    fun part2() {
        val list = str.split("""
""")
         assertEquals(230, day.part2(list))
    }
}