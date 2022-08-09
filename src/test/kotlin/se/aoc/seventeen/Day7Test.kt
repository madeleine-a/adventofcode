package se.aoc.seventeen

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day7Test{
    val str = """
        pbga (66)
        xhth (57)
        ebii (61)
        havc (66)
        ktlj (57)
        fwft (72) -> ktlj, cntj, xhth
        qoyq (66)
        padx (45) -> pbga, havc, qoyq
        tknk (41) -> ugml, padx, fwft
        jptl (61)
        ugml (68) -> gyxo, ebii, jptl
        gyxo (61)
        cntj (57)
    """.trimIndent()

    val d = Day7()

    @Test
    fun part1(){
        assertEquals("tknk", d.part1(fixString(str)))
    }

    @Test
    fun part2(){
        assertEquals(60, d.part2(fixString(str),"tknk"))
    }



    fun fixString(str: String): Map<String, Program> {
        val list = str.split("\n" ).map { it.split("->", "(", ")").map{it.trim()}.filterNot { it == "" }}.sortedBy { it.size }
        val programs = mutableMapOf<String, Program>()
        for(l in list){
            val p = Program(l[0], l[1].toInt())
            if(l.size > 2){
                val programNames: List<String> = l[2].split(",").map { it.replace(" ", "") }
                p.programs = programNames
            }
            programs[l[0]] = p
        }
        return programs
    }
}