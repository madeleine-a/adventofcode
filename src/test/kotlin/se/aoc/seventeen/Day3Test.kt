package se.aoc.seventeen

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class Day3Test {
    private val d = Day3()

    @ParameterizedTest
    @CsvSource("1, 0", "12, 3", "23, 2", "1024, 31")
    fun part1(no: String, steps: Int) {
        assertEquals(steps, d.part1(no))
    }

   @Test
   fun part1_1024(){
      assertEquals(31, d.part1("1024"))
   }

    @Test
    fun part2(){
        val points = d.createSquare_V2(806)
        d.drawSquare(points)

    }
}