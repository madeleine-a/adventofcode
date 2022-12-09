package se.aoc.twentytwo

import se.aoc.Point


fun main() {
    val d = Day8()
    d.run()
}

class Day8 {
    lateinit var data: List<List<Int>>
    var width = -1
    var height = -1
    lateinit var points: List<Point>
    fun run() {
        val input = Day2::class.java.getResource("/twentytwo/day8.txt").readText()
        val list = createList(input)
        println("Day 8")
        println("Part 1")
        println(part1())

        println("Part 8")
        println(part2())
    }

    fun createList(str: String) {
        data = str.split(System.lineSeparator()).map { it.toList().map { it1 -> it1.digitToInt() } }
        height = data.size-1
        width = data[0].size-1
        val l = mutableListOf<Point>()
        data.forEachIndexed { rIndex, r ->
            r.forEachIndexed { cIndex, c ->
                l.add(Point(rIndex, cIndex, c))
            }
        }
        points = l.toList()
    }

    fun part1(): Number {
        return points.count { p -> isVisible(p) }
    }

    private fun getFirstBlock(p: Point, list: List<Point>): Point?{
        return list.firstOrNull { it.value!! >= p.value!! }
    }
    private fun getScenticScore(p: Point): Int {
        return if (p.x == 0) 0
        else if (p.x == width) 0
        else if (p.y == 0 || p.y == height) 0
        else {

            // Top
            var subList = points.filter { it.x < p.x && it.y == p.y }.sortedByDescending { it.x }
            var block = getFirstBlock(p, subList)
            val top = if(block == null) p.x else p.x - (block.x)
            // Bottom
            subList = points.filter { it.x > p.x && it.y == p.y }.sortedBy { it.x }
            block = getFirstBlock(p, subList)
            val bottom = if(block == null) height-p.x else (block.x) - p.x
            // left
            subList = points.filter { it.x == p.x && it.y < p.y }.sortedByDescending { it.y }
            block = getFirstBlock(p, subList)
            val left = if(block == null) p.y else p.y - (block.y)
            // right
            subList = points.filter { it.x == p.x && it.y > p.y }.sortedBy { it.y }
            block = getFirstBlock(p, subList)
            val right = if(block == null) width - p.y else  (block.y) - p.y

           // println("P: $p, left: $left, right, $right, top $top, bottom $bottom")
            return left*right*top*bottom
        }
    }

    private fun isVisible(p: Point): Boolean {
        return if (p.x == 0) true
        else if (p.x == width) true
        else if (p.y == 0 || p.y == height) true
        else {

            // Top
            var subList = points.filter { it.x < p.x && it.y == p.y }
            val top = subList.count { it.value!! < p.value!! } == subList.size
            // Bottom
            subList = points.filter { it.x > p.x && it.y == p.y }
            val bottom = subList.count { it.value!! < p.value!! } == subList.size
            // Left
            subList = points.filter { it.x == p.x && it.y < p.y }
            val left = subList.count { it.value!! < p.value!! } == subList.size
            // Right
            subList = points.filter { it.x == p.x && it.y > p.y }
            val right = subList.count { it.value!! < p.value!! } == subList.size
            //println("P: $p, left: $left, right, $right, top $top, bottom $bottom")
            return top || bottom || left || right
        }
    }

    fun part2(): Number {
      return  points.maxOf { getScenticScore(it) }

    }


}

