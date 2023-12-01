package se.aoc.twentythree

import se.aoc.twentytwo.Day12

fun main() {
    val d = Day1()
    d.run()
}

class Day1 {
    private val numbers = mapOf(
        "one" to 1, "two" to 2, "three" to 3, "four" to 4, "five" to 5, "six" to 6,
        "seven" to 7, "eight" to 8, "nine" to 9
    )

    fun run() {
        val input = Day12::class.java.getResource("/twentythree/day1.txt").readText()
        val list = createList(input)
        println("Part 1")
        println(part1(list)) // Expeced: 54331

        println("Part 2")
        println(part2(list)) // 54518
    }

    fun createList(str: String): List<String> {
        return str.split("\r\n")
    }

    fun part1(input: List<String>): Number {
        return input.map { it.filter { c -> c.isDigit() } }.sumOf { "${it.first()}${it.last()}".toInt() }
    }

    fun part2(input: List<String>): Number {
        return input.map { str ->
            var s = str
            numbers.keys.forEach { no ->
                if (s.contains(no)) {
                    s = s.replace(no, "$no${numbers[no].toString()}$no")
                }
            }
            s.filter { c -> c.isDigit() }
        }.sumOf { v -> "${v.first()}${v.last()}".toInt() }
    }
}
