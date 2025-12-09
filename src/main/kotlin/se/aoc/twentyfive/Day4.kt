package se.aoc.twentyfive

import se.aoc.twentyfour.Point


fun main() {
    val d = Day4()
    d.run()
}


class Day4 {
    fun run() {
        val input = Day4::class.java.getResource("/twentyfive/4.txt").readText()
        val list = createList(input)
        println("Part 1")
        println(part1(list))

        println("Part 2")
        println(part2(list))
    }

    fun parseToPoints(str: String): List<Point> {
        val res = str.lines().mapIndexed { y, s ->
            s.mapIndexed { x, c ->
                Point(x, y, c)
            }
        }
        return res.flatten()
    }

    fun createList(str: String): List<Point> {
        return parseToPoints(str)
    }

    fun part1(points: List<Point>): Int {
        return points.count() - removeAccessiblePoints(points).count()
    }

    private fun removeAccessiblePoints(points: List<Point>): List<Point> {
        val res = points
            .filter {
                !(it.value == '@' && it.getAdjacentPoints(points.asSequence()).filter { p -> p.value == '@' }.count() < 4)
            }
        return res
    }

    fun part2(aPointList: List<Point>): Long {
        var count = 0L
        var doContinue = true
        var points = aPointList

        while (doContinue) {
            val unAccessiblePoints = removeAccessiblePoints(points)
            if (points.count() == unAccessiblePoints.count()) {
                doContinue = false
            } else {
                count += (points.count() - unAccessiblePoints.count())
                points = unAccessiblePoints
            }
        }

        return count
    }

}
