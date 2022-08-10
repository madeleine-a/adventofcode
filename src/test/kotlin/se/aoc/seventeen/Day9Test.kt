package se.aoc.seventeen

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class Day9Test{

    val d = Day9()
    @ParameterizedTest
    @CsvSource("{}; 1", "{{{}}}; 6", "{{},{}}; 5", "{{{},{},{{}}}}; 16",
    "{<a>,<a>,<a>,<a>}; 1", "{{<ab>},{<ab>},{<ab>},{<ab>}}; 9", "{{<!!>},{<!!>},{<!!>},{<!!>}}; 9",
    "{{<a!>},{<a!>},{<a!>},{<ab>}}; 3", "{<{},{},{{}}>}; 1", "{{<!>},{<!>},{<!>},{<a>}}; 3", delimiter = ';' )
    fun part1(str: String, result: Int){
        assertEquals(result, d.part1(str.toList()))

    }

    @Test
    fun toChildren(){
        val str = "{{{{{}}{{{{}}{}{{{}}{}}}{{{{{}}}{}}}{{{}}}}}{{{{}{}{{}{{}}{}}}{{}}{}}{{{{}}}{{}}}{{{}}}}{{{{}{{}}{{}{{}}{{{{{}{{}{}}{{}}}{{}}}{{{}{}}}{{}{}}}{{{}}{{}}}{{}}}}}}{{{}}{{}}{{}}}{}{{{}}}}}}"
        val g = Group(d.toChildren(str, 2).second, 1)
        println(g.writeString())
        println(g)
    }
}