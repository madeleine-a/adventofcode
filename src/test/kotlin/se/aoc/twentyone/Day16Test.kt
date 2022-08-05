package se.aoc.twentyone

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class Day16Test {
val day = Day16()
    @Test
    fun run() {
    }

    @Test
    fun part1() {
        var str = "8A004A801A8002F478"
        day.part1(str)
    }

    @ParameterizedTest
    @CsvSource("D2FE28", "38006F45291200", "EE00D40C823060", "A0016C880162017C3686B18A3D4780",
        "C0015000016115A2E0802F182340", "620080001611562C8802118E34", "8A004A801A8002F478")
    fun part1V2(str: String){
        day.part1(str)
    }

    @Test
    fun part2() {
    }
}