package se.aoc.twentyone.day9

class Point(var x: Int, var y: Int, var value: Int) {
    var isLowest: Boolean = false
    var adjacentPoints: MutableList<Point> = mutableListOf()

    fun offset(aX: Int, anY: Int) {
        this.x += (aX)
        this.y += (anY)
    }

    fun getRiskLevel(): Int {
        return value + 1
    }

    fun getAdjacentPoints(points: List<Point>): List<Point> {
        this.adjacentPoints.addAll(points.filter {
            it.value != 9 && (
                    it.x == this.x - 1 && it.y == this.y
                            || it.x == this.x + 1 && it.y == this.y
                            || it.x == this.x && it.y == this.y + 1
                            || it.x == this.x && it.y == this.y - 1)
        })
        return this.adjacentPoints
    }

    fun calculateIsLowest(points: List<Point>) {
        if (adjacentPoints.isEmpty()) {
            getAdjacentPoints(points)
        }
        this.isLowest = adjacentPoints.none { p -> p.value <= this.value }
    }

    fun getBasinIfLowPoint(points: MutableList<Point>): List<Point> {
        getAdjacentPoints(points)
        if (this.isLowest) {
            val list = this.getNextInBasin(points) + this
            return list.distinct()
        }
        return emptyList()
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

    override fun toString(): String {
        return "Point(x=$x, y=$y, value=$value, isLowest=$isLowest)"
    }


}

private fun Point.getNextInBasin(points: MutableList<Point>): List<Point> {

    val list = mutableListOf<Point>()
    val returnList = mutableListOf<Point>()
    list.addAll(adjacentPoints.filter { p ->
        this.value + 1 == p.value && p.value < 9
    })
    points.removeAll(list)
    list.forEach {
        returnList.addAll(it.getNextInBasin(points))
    }
    returnList.addAll(list)
    return returnList
}