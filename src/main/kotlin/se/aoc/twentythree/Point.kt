package se.aoc.twentythree

import kotlin.math.absoluteValue

class Point(var x: Int, var y: Int, var value: String) {
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

    fun getAdjacentPoints(points: List<Point>): List<Point> {
        val adjacentPoints = mutableListOf<Point>()
        adjacentPoints.addAll(points.filter {
            (
                    it.x == this.x - 1 && it.y == this.y
                            || it.x == this.x + 1 && it.y == this.y
                            || it.x == this.x && it.y == this.y + 1
                            || it.x == this.x && it.y == this.y - 1
                            || it.x == this.x - 1 && it.y == this.y - 1
                            || it.x == this.x + 1 && it.y == this.y + 1
                            || it.x == this.x + 1 && it.y == this.y - 1
                            || it.x == this.x - 1 && it.y == this.y + 1)
        })
        return adjacentPoints
    }
}