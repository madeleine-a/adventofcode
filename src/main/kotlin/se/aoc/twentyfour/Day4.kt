package se.aoc.twentyfour

import se.aoc.Direction


fun main() {
    val d = Day4()
    d.run()
}

class Day4 {

    fun run() {
        val input = Day3::class.java.getResource("/twentyfour/day4.txt").readText()
        val points = parseToPoints(input)
        println("Part 1")
        println(part1(points)) // Expeced: 168539636

        println("Part 2")
        println(part2(points)) // Expeced: 97529391
    }

    companion object {
        val WORD = listOf('X', 'M', 'A', 'S')

    }

    fun parseToPoints(str: String): Sequence<Point> {
        val res = str.lines().mapIndexed { y, s ->
            s.mapIndexed { x, c ->
                Point(x, y, c)
            }
        }
        return res.flatten().asSequence()
    }

    fun part1(points: Sequence<Point>): Number {
        var counter = 0L

        val firstPoints = points.filter { it.value == WORD.first() }
        firstPoints.iterator().forEachRemaining {
            val found = findPoints(it to null, points, WORD.subList(1, WORD.size))
            counter += found
        }
        return counter
    }

    private fun findPoints(
        point: Pair<Point, Direction?>,
        allPoints: Sequence<Point>, list: List<Char>
    ): Long {
        var count = 0L
        val points = if (point.second == null) {
            point.first.getAdjacentPointsWithDirection(allPoints)
                .filter { it.first.value == list.first() }
        } else {
            point.first.getAdjacentPointsInDirection(allPoints, point.second!!)
                .filter { it.first.value == list.first() }
        }
        if (list.size == 1) {
            count += points.count()
        } else {
            points.forEach { p ->
                count += findPoints(p, allPoints, list.subList(1, list.size))
            }
        }
        return count
    }

    fun part2(points: Sequence<Point>): Number {
        val okDirs = listOf(Direction.VDL, Direction.VDR, Direction.VUL, Direction.VUR)

        val firstPoints = points.filter { it.value == 'A' }
        val result = firstPoints.map { point ->
            val points = point.getAdjacentPointsWithDirection(points).filter { pair ->
                (pair.first.value == 'M' || pair.first.value == 'S') &&
                        okDirs.contains(pair.second)
            }.map { it.first }
            isXmas(points, point)
        }.filter { it }.count()
        return result.toLong()
    }

    fun isXmas(points: Sequence<Point>, thePoint: Point): Boolean {
        if (points.count() != 4) return false
        val mPoints = points.filter { it.value == 'M' }
        val sPoints = points.filter { it.value == 'S' }
        if (mPoints.count() != 2 || sPoints.count() != 2) return false
        return mPoints.map { mPoint ->
            mPoint.isDiagonal(sPoints.first()) || mPoint.isDiagonal(sPoints.last())

        }.reduce { acc, b -> acc && b }

    }

}
