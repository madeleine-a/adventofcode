package se.aoc.twentyone.day11

class Octupus(var x: Int, var y: Int, var value: Int) {
    var adjacentOctupuses: MutableList<Octupus> = mutableListOf()
    var flashes: MutableMap<Int, Boolean> = mutableMapOf()

    fun increaseLevel(step: Int) {
        if(flashes[step] != true ) {
            value += 1
        }
    }

    fun flash(step: Int) {
        if (value > 9) {
            value = 0
            flashes[step] = true
            adjacentOctupuses.forEach { o ->
                o.increaseLevel(step)
                o.flash(step)
            }

        }
    }

    fun getAdjacentPoints(octupuses: List<Octupus>): List<Octupus> {
        this.adjacentOctupuses.addAll(octupuses.filter {
            it.x == this.x - 1 && it.y == this.y
                    || it.x == this.x + 1 && it.y == this.y // same row
                    || it.x -1 == this.x && it.y == this.y + 1
                    || it.x +1 == this.x && it.y == this.y + 1
                    || it.x == this.x && it.y == this.y + 1 // row below
                    || it.x -1 == this.x && it.y == this.y -1
                    || it.x +1 == this.x && it.y == this.y -1
                    || it.x == this.x && it.y == this.y -1 // row above
        })

        return this.adjacentOctupuses
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Octupus

        if (x != other.x) return false
        if (y != other.y) return false
        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        result = 31 * result + value
        return result
    }

    override fun toString(): String {
        return "Point(x=$x, y=$y, value=$value)"
    }
}