package se.aoc.seventeen

import se.aoc.Point
import kotlin.math.absoluteValue

fun main() {
    val d = Day3()
    d.run()
}

class Day3 {
    fun run() {
        val input = "361527"

        println(input)
        println("Part 1")
        //println(part1(input))

        println("Part 2")
        println(part2(input))
    }

    private fun calculateManhattanDistance(p1: Point, p2: Point): Int{
        return (p1.x - p2.x).absoluteValue + (p1.y - p2.y).absoluteValue
    }

    /**
     * Result should be a square like:
     *
     * 17  16  15  14  13
     * 18   5   4   3  12
     * 19   6   1   2  11
     * 20   7   8   9  10
     * 21  22  23 ---> ...
     */
    private fun createSquare(no: Int): List<Point> {
        var points = mutableListOf<Point>()
        var dist = 1
        var dirX = 1
        var dirY = 1
        var x = 0
        var y = 0
        var noOfX = 0
        var noOfY = 0
        for (i in 1 .. no) {
            points.add(Point(x, y, i))
            if(noOfX == dist && noOfY < dist){
                y+=dirY
                noOfY ++
            }
            if(noOfX < dist) {
                x += dirX
                noOfX++
            }

            if(noOfY == dist){
                dist ++
                noOfX = 0
                noOfY = 0
                dirX =-dirX
                dirY =-dirY
            }
        }

        return points
    }

    fun sumPoints(points: List<Point>): Int{
        val sum = points.sumOf { it.value!! }
        return if(sum == 0) 1 else sum
    }

    /**
     * Result should be a square like:
     *
     * 147  142  133  122   59
     * 304    5    4    2   57
     * 330   10    1    1   54
     * 351   11   23   25   26
     * 362  747  806--->   ...
     */
    fun createSquare_V2(no: Int): List<Point> {
        var points = mutableListOf<Point>()
        var dist = 1
        var direction = 1
        var x = 0
        var y = 0
        var noOfX = 0
        var noOfY = 0
        var i = 1

        while(i < no){
            val p = Point(x, y, i)
            points.add(p)
            i = sumPoints(p.getAdjacentPoints(points))
            p.value = i
            if(noOfX == dist && noOfY < dist){
                y+=direction
                noOfY ++
            }
            if(noOfX < dist) {
                x += direction
                noOfX++
            }

            if(noOfY == dist){
                dist ++
                noOfX = 0
                noOfY = 0
                direction =-direction
            }
        }

        if(i <= no) {
            // Add the last one after
            val p = Point(x, y, i)
            points.add(p)
            i = sumPoints(p.getAdjacentPoints(points))
            p.value = i
        }

        return points
    }

    fun drawSquare(points: List<Point>) {
        var pointsToWrite = points.sortedWith(compareBy { it.y }).asReversed().sortedWith(compareBy { it.x })
        var writeList = pointsToWrite.groupBy { it.y }
        val max = writeList.map { it.value.size }.maxOrNull()
        for (l in writeList) {
            var s = ""

            for (p in l.value) {
                s += p.value
                s += " "
                s += " ".repeat(points.size - p.value.toString().length)
            }
            println(s)
        }
    }

    fun part1(input: String): Number {
        val points = createSquare(input.toInt())
        // Get Points
        val p1 = points.first { it.value == 1 }
        val p2 = points.first { it.value == input.toInt() }
        return calculateManhattanDistance(p2, p1)
    }

    fun part2(input: String): Number {
        val points = createSquare_V2(input.toInt())
        return points.last().value!!
    }

}