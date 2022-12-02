package se.aoc.twentytwo

import se.aoc.seventeen.Day2


fun main() {
    val d = Day1()
    d.run()
}

class Day1 {

    fun run() {
        val input = Day1::class.java.getResource("/twentytwo/day1.txt")!!.readText()
        val list = createList(input)
        println("Part 1")
        println(part1(list))

        println("Part 2")
        println(part2(list))
    }

    fun createList(str: String): List<List<Int>>{
        return str.split("\r\n\r\n").map { it.split("\r\n").map { it.toInt() } }
    }

    fun part1(input: List<List<Int>>): Number {
        return input.maxOf { it.sum() }
    }


    fun part2(input: List<List<Int>>): Number {
        return input.map { it.sum() }.sortedDescending().take(3).sum()
    }
}

