package se.aoc.twentyone.day9

import se.aoc.twentyone.Stack
import se.aoc.twentyone.push
import se.aoc.twentyone.pushAll

class Day9 {
    lateinit var points: List<Point>
    fun run() {
        val input = Day9::class.java.getResource("/twentyone/day9.txt").readText()

        println("Part 1")
        println(part1(input))

        println("Part 2")
        println(part2(input))
    }

    private fun init(input: String) {
        val list = input.split("\r\n").map { it.toList().map { c -> c.digitToInt() } }
        val pointList = mutableListOf<Point>()
        for (x in list.indices) {
            for (y in list[x].indices) {
                pointList.add(Point(x, y, list[x][y]))
            }
        }
        pointList.forEach { p -> p.calculateIsLowest(pointList) }
        points = pointList
    }

    fun part1(str: String): Long {
        init(str)
        return points.filter { it.isLowest }.map { it.getRiskLevel().toLong() }.sum()
    }

    fun part2(str: String): Number {
        init(str)
        val stack: Stack<Point> = mutableListOf()
        val lowPoints = points.filter { it.isLowest }

        val result = lowPoints.map { lp ->
            stack.push(lp)
            val basin = lp.getBasinIfLowPoint(points.filter { p -> !stack.contains(p) } as MutableList<Point>)
            stack.pushAll(basin)
            basin
        }

        println(result.sortedByDescending {  it.size }.windowed(3, 1, false).first().map { it.size })
        return result.asSequence().sortedByDescending {  it.size }.windowed(3, 1, false).first()
            .map { it.size }.reduce { acc, l ->
            acc * l
        }

    }

}


