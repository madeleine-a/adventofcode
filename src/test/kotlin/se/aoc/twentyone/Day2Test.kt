package se.aoc.twentyone

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day2Test{
    val day = Day2()

    val str = """forward 5
down 5
forward 8
up 3
down 8
forward 2""".trimIndent()


    @Test
    fun part1(){
        val list = str.split(
            """
"""
        )
        assertEquals(150, day.part1(list))
    }

    @Test
    fun part2(){
        val list = str.split(
            """
"""
        )
        assertEquals(900, day.part2(list))
    }

    @Test
    fun run(){
        day.run()
    }
}

