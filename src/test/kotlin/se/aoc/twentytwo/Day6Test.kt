package se.aoc.twentytwo

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class Day6Test {
    val day = Day6()

    @Test
    fun part1() {
        val s = "mjqjpqmgbljsphdztnvjfqwrcgsmlb"
        day.createList(s)
        assertEquals(7, day.part1())
    }

    @ParameterizedTest
    @CsvSource("bvwbjplbgvbhsrlpgdmjqwftvncz,5", "nppdvjthqldpwncqszvftbrmjlhg, 6", "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg, 10",
    "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw, 11")
    fun part1_1(s: String, result: Int) {
        day.createList(s)
        assertEquals(result, day.part1())
    }

    @ParameterizedTest
    @CsvSource("mjqjpqmgbljsphdztnvjfqwrcgsmlb, 19", "bvwbjplbgvbhsrlpgdmjqwftvncz,23", "nppdvjthqldpwncqszvftbrmjlhg, 23", "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg, 29",
        "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw, 26")
    fun part2(s: String, result: Int) {
        day.createList(s)
        assertEquals(result, day.part2())
    }
}

