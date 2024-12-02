package se.aoc.twentyfour

import kotlin.math.absoluteValue


fun main() {
    val d = Day1()
    d.run()
}

class Day1 {


    fun run() {
        val input = Day1::class.java.getResource("/twentyfour/day1.txt").readText()
        val list = createList(input)
        println(list)
        println("Part 1")
        println(part1(list)) // Expeced: 54331

        println("Part 2")
        println(part2(list)) // 54518
    }

    fun createList(str: String): List<Pair<Int, Int>> {
        val list = str.split("\n").map {
            it.split(" ")
                .filter { it.isNotBlank() }
        }.map { it[0].toInt() to it[1].toInt() }.toList()

        val left = list.map { it.first }.sorted().toList()
        val right = list.map { it.second }.sorted().toList()
        return left.zip(right)

    }

    fun part1(list: List<Pair<Int, Int>>): Number {
        return list.sumOf { (it.first - it.second).absoluteValue }
    }

    fun part2(list: List<Pair<Int, Int>>): Number {
        val number = list.map { it.first}.toList()
        val times = list.map{ it.second }.groupingBy { it }.eachCount()
        return number.sumOf { it * (times[it] ?: 0) }
    }
}
