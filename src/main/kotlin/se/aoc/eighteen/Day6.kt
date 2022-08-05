package se.aoc.eighteen

import se.aoc.Point

class Day6 {
    fun run() {
        val input = Day6::class.java.getResource("/eighteen/day6.txt").readText()
        val list = input.split("\r\n")
        println("Part 1")
        println(part1(list))

        println("Part 2")
        println(part2(list))
    }

    lateinit var points: List<Point>

    private fun init(input: List<String>) {
        points = input.map { p -> p.split(",").map { it.trim() } }
            .map { Point(it[0].toInt(), it[1].toInt()) }
    }

    fun part1(input: List<String>): Number {
        init(input)
        // Which points have 4 points around them

        return 0
    }

    fun part2(input: List<String>): Number {
        init(input)
        return 0
    }


}