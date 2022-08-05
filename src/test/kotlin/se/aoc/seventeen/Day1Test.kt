package se.aoc.seventeen

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class Day1Test {

    @ParameterizedTest
    @CsvSource("1212, 6", "1221, 0", "123425, 4", "123123, 12", "12131415, 4")
    fun part2(input: String, result: Int) {
        val list = input.toList().map { c -> c.digitToInt() }.toList()
        val d = Day1()
        assertEquals(result, d.part2(list))
    }
}