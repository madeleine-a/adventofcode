package se.aoc.twentyone

import se.aoc.Point

class Day5 {
    var vents = mutableListOf<List<Point>>()

    fun run() {
        val input = Day5::class.java.getResource("/twentyone/day5.txt").readText()
        println("Part 1")
        println(part1(input))

        println("Part 2")
        println(part2(input))
    }

    private fun init(input: String) {
        vents.addAll(input.split("\r\n").map {
            it.split("->")
                .map { s -> s.trim().trim().split(",") }
        }.map { v ->
            val pA = Point(v.first()[0].toInt(), v.first()[1].toInt())
            val pB = Point(v.last()[0].toInt(), v.last()[1].toInt())
            listOf(pA, pB)
        })
    }

    fun part1(input: String): Number {
        if (vents.isEmpty()) init(input)
        return vents.filter { v -> v.first().isHorizontalOrVertical(v.last()) }.map { v ->
            pointListToRange(v)
        }.flatten().groupingBy { it }.eachCount().filter { it.value > 1 }.count()
    }

    fun part2(input: String): Number {
        if (vents.isEmpty()) init(input)
        val r = vents.filter { v ->
            v.first().isHorizontalOrVertical(v.last())
                    || v.first().isDiagonal(v.last())
        }.map { v ->
            pointListToRange(v)
        }.flatten().groupingBy { it }.eachCount().filter { it.value > 1 }
        return r.count()
    }

    private fun pointListToRange(points: List<Point>): List<Point> {
        return points.first().toRange(points.last())
    }
}


