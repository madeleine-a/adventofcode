package se.aoc.twentyone

import se.aoc.Point
import kotlin.math.absoluteValue

class Day13 {
    private var points = mutableListOf<Point>()
    private val folds = mutableListOf<Pair<String, Int>>()
    fun run() {
        val input = Day13::class.java.getResource("/twentyone/day13.txt").readText()
        val list = input.split("\r\n")

        println("Part 1")
        println(part1(list))

        println("Part 2")
        println(part2(list))
    }

    fun init(list: List<String>) {
        for (s in list) {
            if (!s.contains("fold along") && s.isNotEmpty()) {
                val p = s.split(",")
                var point = Point(p.first().toInt(), p.last().toInt())
                point.on = true
                points.add(Point(p.first().toInt(), p.last().toInt()))
            } else if (s.isNotEmpty()) {
                val f = s.removePrefix("fold along ").split("=")
                folds.add(Pair(f.first(), f.last().toInt()))
            }
        }
        points = points.sortedWith(compareBy({ it.y }, { it.x })).toMutableList()

    }

    fun writePoints() {
        points = points.sortedWith(compareBy({ it.y }, { it.x })).toMutableList()
        var writeList = points.groupBy { it.y }.toSortedMap()
        println(writeList)
        var maxX = points.maxOf { it.x }
        println(maxX)
        for (l in writeList) {
            var x = 0
            var s = ""
            for (p in l.value) {
                s += ".".repeat(p.x - x) + "#"
                x = p.x + 1
            }
            if (x <= maxX) {
                s += ".".repeat(maxX - x + 1)
            }

            println(s)
        }
    }

    fun part1(list: List<String>): Number {
        init(list)
        fold(listOf(folds.first()))
        return points.size
    }

    private fun fold(folds: List<Pair<String, Int>>) {
        for (f in folds) {
            val no = f.second
            if (f.first == "y") {
                val pointsToTurn = points.filter { it.y > no }
                val otherPoints = points.filter { it.y < no }
                pointsToTurn.map { p -> p.y = (p.y - (p.y - no) * 2).absoluteValue }
                points = (otherPoints + pointsToTurn).distinct().toMutableList()

            } else {
                val pointsToTurn = points.filter { it.x > no }
                val otherPoints = points.filter { it.x < no }
                pointsToTurn.map { p -> p.x = (p.x - (p.x - no) * 2).absoluteValue }
                points = (otherPoints + pointsToTurn).distinct().toMutableList()
            }

        }
    }

    fun part2(list: List<String>): Number {
        init(list)
        fold(folds)
        writePoints()
        return points.size
    }

}


