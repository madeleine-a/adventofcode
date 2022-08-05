package se.aoc.eighteen

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day2Test {
    val day = Day2()
    val str = """abcdef
    bababc
    abbcde
    abcccd
    aabcdd
    abcdee
    ababab
""".trimMargin()

    val str2 = """abcde
    fghij
    klmno
    pqrst
    fguij
    axcye
    wvxyz
""".trimIndent()

    @Test
    fun run() {
        day.run()
    }


    @Test
    fun part1() {
        val list = str.split(
            """
    """
        )
        day.part1(list)
    }

    @Test
    fun part2() {
        val list = str2.split("""
    """)
        assertEquals("fgij", day.part2(list))
    }
}