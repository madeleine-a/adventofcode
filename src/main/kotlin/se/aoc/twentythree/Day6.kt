package se.aoc.twentythree

import splitStrOfNumbersToList


fun main() {
    val d = Day6()
    d.run()
}

class Day6 {
    fun run() {
        val input = """Time:        38     94     79     70
            Distance:   241   1549   1074   1091
        """.trimIndent()
        val list = createList(input)
        println("Part 1")
        println(part1(list)) // 1083852

        println("Part 2")
        println(part2(mapOf( 38947970L to 241154910741091L))) // 54518
    }

    fun createList(str: String): Map<Long, Long> {
        var res = str.lines().map {
            it.split(":").drop(1)
                .map { it.splitStrOfNumbersToList() }.flatten()
        }
        return  res[0].zip(res[1]).toMap()
    }

    fun part1(input: Map<Long, Long>): Number {
        return beatRecord(input)
    }

    fun part2(input: Map<Long, Long>): Number {
        return beatRecord(input)
    }

    private fun beatRecord(input: Map<Long, Long>): Long {
        return input.map { (time, dist) ->
            getPossibleDistances(time).filter { it>dist }.size
        }.fold(1L) { acc, l -> acc*l }
    }

    private fun getPossibleDistances(no: Long): List<Long> {
        return (0..no).map { (no-it)*it }
    }
}

