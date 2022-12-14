package se.aoc

import kotlin.math.absoluteValue

class Point(var x: Int, var y: Int, var value: Int? = null) {
    fun offset(aX: Int, anY: Int) {
        this.x += (aX)
        this.y += (anY)
    }

    fun withOffset(aX: Int, anY: Int): Point {
        val p = this.clone()
        p.x += (aX)
        p.y += (anY)
        return p
    }

    var depth = 0
    var on = false

    fun aim(aX: Int, anY: Int) {
        this.x += (aX)
        this.y += (anY)
        if (aX > 0) {
            this.depth += aX * this.y
        }
    }

    fun clone(): Point {
        return Point(this.x, this.y)
    }

    override fun toString(): String {
        return "Point(x=$x, y=$y) - V: $value"
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

    fun isHorizontalOrVertical(other: Point): Boolean {
        return isHorizontal(other) || isVertical(other)
    }

    private fun isHorizontal(other: Point): Boolean {
        return this.y == other.y
    }

    private fun isVertical(other: Point): Boolean {
        return this.x == other.x
    }

    fun isDiagonal(other: Point): Boolean {
        return (this.x == this.y && other.x == other.y)
                || (this.x == other.y && this.y == other.x)
                || ((this.x - other.x).absoluteValue == (this.y - other.y).absoluteValue)

    }

    fun toRange(other: Point): List<Point> {
        val range = mutableListOf<Point>()
        if (this.isVertical(other)) {
            val y1 = if (this.y < other.y) this.y else other.y
            val y2 = if (this.y > other.y) this.y else other.y
            for (y in y1..y2) {
                range.add(Point(x, y))
            }
        } else if (this.isHorizontal(other)) {
            val x1 = if (this.x < other.x) this.x else other.x
            val x2 = if (this.x > other.x) this.x else other.x
            for (x in x1..x2) {
                range.add(Point(x, y))
            }
        } else if (this.isDiagonal(other)) {
            val pLow = if (this.x < other.x) this else other
            val pHigh = if (this.x > other.x) this else other
            val doSub = (pLow.y > pHigh.y)
            for ((i, x) in (pLow.x..pHigh.x).withIndex()) {
                if (doSub) {
                    range.add(Point(x, (pLow.y - i)))
                } else {
                    range.add(Point(x, (pLow.y + i).absoluteValue))
                }
            }
        }
        return range
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