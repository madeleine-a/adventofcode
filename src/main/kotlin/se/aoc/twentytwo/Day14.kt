package se.aoc.twentytwo

import se.aoc.Point
import se.aoc.Stack
import se.aoc.twentytwo.FallDirection.*


fun main() {
    val d = Day14()
    d.run()
}

class Day14 {
    lateinit var points: List<Point>
    var haveFloor = false
    var maxX: Int = 0
    lateinit var edges: List<Point>
    fun run() {
        val input = Day14::class.java.getResource("/twentytwo/day14.txt").readText()
        createList(input)
        println("Day 14")
        println("Part 1")
        println(part1())

        println("Part 2")
        println(part2())
    }

    fun createList(str: String) {
        val l = str.split(System.lineSeparator()).map { row ->
            row.split(" -> ")
                .map { it.split(",") }.map { no -> Point(no.last().toInt(), no.first().toInt()) }
        }
        points = l.map { list ->
            list.windowed(2, 1, false).map { it.first().toRange(it.last()) }.flatten().distinct()
        }.flatten()

        maxX = points.maxByOrNull { it.x }!!.x
        edges = getEdge()

        // printOut(mutableListOf())
    }

    fun part1(): Number {
        return pourSand()
    }

    fun part2(): Number {
        val x = points.maxByOrNull { it.x }!!.x + 2
        edges = getEdge().sortedBy { it.y }
        val floor = Point(x, edges.first().y - x-x).toRange(Point(x, edges.last().y + x +x))

        haveFloor = true
        points = points + floor
        maxX = x
        return pourSand()
    }

    fun printOut(restedSand: MutableList<Point>) {
        val edges = getEdge()
        val list = points + restedSand
        val printList = list.groupBy { p -> p.x }.toSortedMap().map { row ->
            val sorted = row.value.sortedBy { it.y }
            var str = ""
            for (i in edges.first().y..edges.last().y) {
                val p = sorted.firstOrNull { it.y == i }
                str += if (p != null) {
                    if (p.value == 0) "o" else "#"
                } else " "
            }
            str to row.key
        }
        printList.forEach {
            val space = " ".repeat(4 - it.second.toString().length)
            println("${it.second}$space|${it.first}|")
        }
    }

    fun pourSand(): Int {
        val restedSand: Stack<Point> = mutableListOf()
        var keepPouring = true
        while (keepPouring) {
            val sand = Point(0, 500, 0)
            keepPouring = fall(sand, restedSand)
            if(restedSand.size%500 == 0){
                println(sand)
            }
        }
        printOut(restedSand)
        return restedSand.size
    }

    private fun getEdge(): List<Point> {
        val minY = points.minByOrNull { it.y }!!.y
        val maxY = points.maxByOrNull { it.y }!!.y
        val min = points.filter { p -> p.y == minY }.maxByOrNull { p -> p.x }!!
        val max = points.filter { p -> p.y == maxY }.maxByOrNull { p -> p.x }!!
        return listOf(min, max)
    }

    private fun intoTheAbyss(sand: Point): Boolean {
        return if (!haveFloor) {
            edges.map { it.y }.contains(sand.y) || sand.x > maxX
        } else {
            sand.x >= maxX
        }
    }

    private fun fall(sand: Point, restedSand: MutableList<Point>): Boolean {
        var direction = choosePath(sand, restedSand)
        if(direction == STOP) {
            restedSand.add(sand)
            return false
        }
        do {
            sand.offset(direction.x, direction.y)
            if (intoTheAbyss(sand)) {
                println("Nu åker vi förbi $sand")
                return false
            }
            direction = choosePath(sand, restedSand)
        } while (direction != STOP)
        restedSand.add(sand)
        return true
    }


    fun choosePath(sand: Point, restedSand: List<Point>): FallDirection {
        val preferredPaths = mapOf(
            DOWN to sand.withOffset(DOWN.x, DOWN.y),
            LEFT to sand.withOffset(LEFT.x, LEFT.y),
            RIGHT to sand.withOffset(RIGHT.x, RIGHT.y),
        ).toMap()

        return if (!points.contains(preferredPaths[DOWN]) && !restedSand.contains(preferredPaths[DOWN])) {
            DOWN
        } else if (!points.contains(preferredPaths[LEFT]) && !restedSand.contains(preferredPaths[LEFT])) {
            LEFT
        } else if (!points.contains(preferredPaths[RIGHT]) && !restedSand.contains(preferredPaths[RIGHT])) {
            RIGHT
        } else {
            STOP
        }
    }


}


enum class FallDirection(val x: Int, val y: Int) {
    DOWN(1, 0), LEFT(1, -1), RIGHT(1, 1), STOP(0, 0)
}
