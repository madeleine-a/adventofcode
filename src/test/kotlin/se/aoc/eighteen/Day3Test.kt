package se.aoc.eighteen

import org.jetbrains.annotations.TestOnly
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day3Test {
    val day = Day3()
    val str = """#1 @ 1,3: 4x4
#2 @ 3,1: 4x4
#3 @ 5,5: 2x2""".trimIndent()

    @Test
    fun run() {
        day.run()
    }

    @Test
    fun part1() {
       val input = str.split("""
""")
        assertEquals(4, day.part1(input))

    }

    @Test
    fun part2() {
        val input = str.split("""
""")
        assertEquals("3", day.part2(input))
    }

    @Test
    fun split(){
        var str = "#2 @ 3,1: 4x4"
        day.split(str)
    }
}