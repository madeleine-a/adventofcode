package se.aoc.twentytwo

fun main() {
    val d = Day9()
    d.run()
}

class Day9 {
    lateinit var data: List<Pair<Direction, Int>>

    fun run() {
        val input = Day9::class.java.getResource("/twentytwo/day9.txt").readText()
        val list = createList(input)
        println("Day 9")
        println("Part 1")
        println(part1())

        println("Part 9")
        println(part2())
    }

    fun createList(str: String) {
        data = str.split(System.lineSeparator()).map {
            val l = it.split(" ")
            Direction.valueOf(l.first().toString()) to l.last().toInt()
        }
    }

    fun part1(): Number {
        val rope = Rope(listOf(Knot(0, 0), Knot(0, 0)))
        return countTailPositions(rope)

    }

    fun part2(): Number {
        val rope = Rope((0 until 10).map { Knot(0,0) }.toList())
        return countTailPositions(rope)
    }

    private fun countTailPositions(rope: Rope): Int {
        val tailPositions = mutableListOf(rope.knots.last().clone())
        data.forEach { motion ->
            for (i in 0 until motion.second) {
                rope.knots.forEachIndexed { index, knot ->
                    if(index == 0){
                        // head
                        knot.offset(motion.first.x, motion.first.y)
                    }else{
                        if (!knot.isAdjacentPoints(rope.knots[index-1]) && knot != rope.knots[index-1]) {
                            knot.approach(rope.knots[index-1])
                        }
                    }
                    if(index == rope.knots.size-1) {
                        tailPositions.add(knot.clone())
                    }
                }
            }
        }
        return tailPositions.distinct().count()
    }



}

data class Rope(val knots: List<Knot>)

class Knot(var x: Int, var y: Int) {
    fun offset(aX: Int, anY: Int) {
        this.x += (aX)
        this.y += (anY)
    }

    fun approach(p: Knot) {
        var x = if (p.x > this.x) 1 else if (p.x < this.x) -1 else 0
        var y = if (p.y > this.y) 1 else if (p.y < this.y) -1 else 0
        this.offset(x, y)
    }

    fun clone(): Knot {
        return Knot(this.x, this.y)
    }

    fun isAdjacentPoints(p: Knot): Boolean {
        return p.x == this.x - 1 && p.y == this.y
                || p.x == this.x + 1 && p.y == this.y
                || p.x == this.x && p.y == this.y + 1
                || p.x == this.x && p.y == this.y - 1
                || p.x == this.x - 1 && p.y == this.y - 1
                || p.x == this.x + 1 && p.y == this.y + 1
                || p.x == this.x + 1 && p.y == this.y - 1
                || p.x == this.x - 1 && p.y == this.y + 1
    }

    override fun toString(): String {
        return "Point(x=$x, y=$y)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as se.aoc.twentytwo.Knot

        if (x != other.x) return false
        if (y != other.y) return false

        return true
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        return result
    }


}

enum class Direction(val x: Int, val y: Int) {
    D(0, -1), U(0, 1), L(-1, 0), R(1, 0)
}

