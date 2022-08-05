package se.aoc.twentyone

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import se.aoc.twentyone.day11.Day11

internal class Day12Test {
    val day = Day12()

    fun getInput(fileName: String): List<String>{
        val input = Day12::class.java.getResource("/twentyone/$fileName").readText()
        return input.split("\r\n")
    }

    @Test
    fun run() {
    }

    @Test
    fun part1() {
        val list = getInput("day12_1.txt")

        day.part1(list)
    }

    @Test
    fun part2() {
    }
}