package se.aoc.seventeen

fun main() {
    val d = Day2()
    d.run()
}

class Day2 {
    fun run() {
        val input = Day2::class.java.getResource("/seventeen/day2.txt").readText()
        val list = input.split("\r\n").map { it.split("\\s".toRegex()).map { c -> c.toInt() } }.toList()
        println(list)
        println("Part 1")
        println(part1(list))

        println("Part 2")
        println(part2(list))
    }

    fun part1(input: List<List<Int>>): Number {
       return input.map { it.sortedDescending() }.sumOf { it.first() - it.last() }
    }

    fun part2(input: List<List<Int>>): Number {
        return input.map { list -> list.map { no -> list.map { no.toFloat().div(it) }
            .filter { res -> res % 1.0 == 0.0 && res.toInt()>1 } } }.flatten().flatten().sum().toInt()
    }

}