package se.aoc.eighteen

import se.aoc.Point

class Day3 {
    fun run() {
        val input = Day3::class.java.getResource("/eighteen/day3.txt").readText()
        val list = input.split("\r\n")
        println("Part 1")
        println(part1(list))
        println("Part 2")
        println(part2(list))
    }

    fun part1(input: List<String>): Number {
        return input.map { split(it) }.map { c -> c.points }.flatten()
            .groupingBy { it }.eachCount().filter { it.value > 1 }.count()
    }

    fun part2(input: List<String>): String {
        val claims = input.map { split(it) }
        val notOverlapping = claims.filter { c -> arePointsNotOverlapping(c, claims) }
        return notOverlapping[0].id
    }

    private fun arePointsNotOverlapping(c: Claim, claims: List<Claim>): Boolean {
        var noOverlap = true
        loop@ for (claim in claims) {
            noOverlap = true
            if (c != claim) {
                 for (p in claim.points) {
                    if (c.points.contains(p)) {
                        noOverlap = false
                        break@loop
                    }
                }
            }
        }
        return noOverlap
        /*  return !c.points.map {  p -> claims.filter { it != c }
              .map{ it.points}.flatten().contains(p)}.reduce{a,b ->  a || b}*/
    }

    fun split(str: String): Claim {
        return Claim(str.trim().split("@", ":", ",", "x").map { it.trim() })
    }
}

data class Claim(val cd: List<String>) {
    val id = cd[0].removePrefix("#")
    val points = createPoints()

    private fun createPoints(): List<Point> {
        val points = mutableListOf<Point>()
        val startPoint = Point(cd[1].toInt(), cd[2].toInt())
        for (i in 1..cd[3].toInt()) {
            for (j in 1..cd[4].toInt()) {
                points.add(Point(startPoint.x + i, startPoint.y + j))
            }
        }
        return points
    }

    override fun toString(): String {
        return "Claim(id='$id', points=$points)"
    }

}