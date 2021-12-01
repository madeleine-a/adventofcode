package se.aoc.twentyone

class Day1 {
    fun run() {
        val input = Day1::class.java.getResource("/twentyone/day1.txt").readText()
        val list = input.split("\r\n").map { it.toInt() }
        println("Part 1")
        println(part1(list))

        println("Part 2")
        println(part2(list))
    }

    fun part1(input: List<Int>): Number {
        val secondList: List<Int> = input.slice(1 until input.size).toList()
        val firstList: List<Int> = input.slice(0 until input.size - 1)
        return secondList.zip(firstList).map { (x, y) ->
            if (x > y) 1 else 0
        }.sum()
    }

    fun part2(input: List<Int>): Number {
        val firstList = input.windowed(3, 1){it.sum()}
        val secondList = firstList.slice(1 until firstList.size)
        return secondList.zip(firstList).map { (x, y) ->
            if (x > y) 1 else 0
        }.sum()
    }
}