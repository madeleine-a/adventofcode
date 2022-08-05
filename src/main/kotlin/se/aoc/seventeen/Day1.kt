package se.aoc.seventeen

fun main() {
    val d = Day1()
    d.run()
}

class Day1 {
    fun run() {
        val input = Day1::class.java.getResource("/seventeen/day1.txt").readText()
        val list = input.toList().map { c -> c.digitToInt() }.toList()
        println("Part 1")
        println(part1(list))

        println("Part 2")
        println(part2(list))
    }

    fun part1(input: List<Int>): Number {
        val moveLastList = mutableListOf(input.last())
        moveLastList.addAll(input.subList(0, input.lastIndex - 1))
        return moveLastList.zipWithNext().filter { it.first == it.second }.sumOf { it.first }
    }

    fun part2(input: List<Int>): Number {
        val length = input.size
        val newList = input.toMutableList()
        newList.addAll(input.subList(0, length/2))
        val numbers = newList.windowed((length/2)+1, 1).map { it.first() to it.last() }.toList()
        return numbers.filter { it.first == it.second }.sumOf { it.first }
    }

}