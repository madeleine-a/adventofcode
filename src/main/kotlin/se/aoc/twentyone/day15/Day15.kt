package se.aoc.twentyone.day15


class Day15 {
    lateinit var graph: List<List<Int>>
    fun run() {
        val input = Day15::class.java.getResource("/twentyone/day15.txt").readText()

        println("Part 1")
        println(part1(input))

        println("Part 2")
        println(part2(input))
    }

    private fun init(input: String) {
        graph = input.split("\r\n").map { it.toList().map { c -> c.digitToInt() } }
        println(graph)
    }

    fun part1(input: String): Number {
        init(input)
return 0
    }


    fun part2(input: String): Number {
        return 0
    }
}