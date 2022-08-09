package se.aoc.seventeen

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day8Test{
    val str = """
        b inc 5 if a > 1
        a inc 1 if b < 5
        c dec -10 if a >= 1
        c inc -20 if c == 10
    """.trimIndent()

    val d = Day8()

    @Test
    fun part1AndPart2(){
        val list = str.split("\n" ).map { it.split(" ") }
            .map { l ->
                Instruction(l[0], l[1], l[2].toInt(), Condition(l[4], l[5], l[6].toInt()))
            }
        d.initRegister(list)
        assertEquals(1, d.part1(list))
        assertEquals(10, d.part2(list))
    }
}