package se.aoc.twentythree

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day3Test {
    val day = Day3()
    val input = Day3::class.java.getResource("/twentythree/day3test.txt").readText()

    @Test
    fun points(){
        val list = day.createList("533...........996.........352..................619%...200......+......................142....408....=....634............379*491.588@..183...")
        println(list.map { it.value })
    }
    @Test
    fun part1() {
        val list = day.createList(input)
        assertEquals(5116, day.part1(list))
    }

    @Test
    fun part2() {
        val list = day.createList(input)
        assertEquals(467835, day.part2(list))
    }
    @Test
    fun foo() {
        val regex = "\\b".toPattern()
        assertEquals("..55..66*88..".replace('.', ' ').split(regex),
            listOf("  ", "55", "  ", "66", "*", "88", "  "))
    }

}

