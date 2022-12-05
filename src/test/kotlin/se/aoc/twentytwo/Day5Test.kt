package se.aoc.twentytwo

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day5Test {
    val day = Day5()

    val input = Day5::class.java.getResource("/twentytwo/day5test.txt").readText()
    val stacks = mutableMapOf<Int, MutableList<Char>>()

    fun initStacks(){
        stacks[1] = mutableListOf('Z', 'N')
        stacks[2] = mutableListOf('M', 'C', 'D')
        stacks[3] = mutableListOf('P')
    }

    @Test
    fun part1() {
        val list = day.createInstructions(input)
        initStacks()
        day.stacks = stacks
        day.instructions = list
        assertEquals("CMZ", day.part1())
    }

    @Test
    fun part2() {
        val list = day.createInstructions(input)
        initStacks()
        day.stacks = stacks
        day.instructions = list
        assertEquals("MCD", day.part2())
    }
}

