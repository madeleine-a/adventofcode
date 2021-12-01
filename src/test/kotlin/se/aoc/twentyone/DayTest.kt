package se.aoc.twentyone

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class DayTest {
    private val day = Day1()

    @Test
    fun part1() {
        val data = """199,200,208,210,200,207,240,269,260,263""".trimIndent()
        val list = data.split(",").map { it.toInt() }
        assertEquals(7, day.part1(list))

    }

    @Test
    fun part2() {
        val data = """199,200,208,210,200,207,240,269,260,263""".trimIndent()
        val list = data.split(",").map { it.toInt() }
        assertEquals(5, day.part2(list))
    }
}