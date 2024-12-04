package se.aoc.twentyfour

import se.aoc.Direction
import kotlin.math.absoluteValue

class Point(var x: Int, var y: Int, var value: Char) {
    override fun toString(): String {
        return "Point(x=$x, y=$y, value=$value)"
    }


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Point

        if (x != other.x) return false
        if (y != other.y) return false

        return true
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        return result
    }

    fun getAdjacentPointsWithDirection(points: Sequence<Point>, ): Sequence<Pair<Point, Direction>>{
        return getAdjacentPoints(points).map { p -> Pair(p, Direction.values().find { it.x == p.x - x && it.y == p.y - y }!!) }
    }

    fun getAdjacentPointsInDirection(points: Sequence<Point>, direction: Direction): Sequence<Pair<Point, Direction>>{
        val points = getAdjacentPoints(points).map { p -> Pair(p, Direction.values().find { it.x == p.x - x && it.y == p.y - y }!!) }
        return points.filter { it.second == direction }
    }

    fun getAdjacentPoints(points: Sequence<Point>): Sequence<Point> {
        return points.filter {
            (
                    it.x == this.x - 1 && it.y == this.y
                            || it.x == this.x + 1 && it.y == this.y
                            || it.x == this.x && it.y == this.y + 1
                            || it.x == this.x && it.y == this.y - 1
                            || it.x == this.x - 1 && it.y == this.y - 1
                            || it.x == this.x + 1 && it.y == this.y + 1
                            || it.x == this.x + 1 && it.y == this.y - 1
                            || it.x == this.x - 1 && it.y == this.y + 1)
        }
    }

    fun isDiagonal(other: Point): Boolean {
        return (this.x == this.y && other.x == other.y)
                || (this.x == other.y && this.y == other.x)
                || ((this.x - other.x).absoluteValue == (this.y - other.y).absoluteValue)

    }
}