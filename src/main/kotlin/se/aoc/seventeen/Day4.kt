package se.aoc.seventeen

fun main() {
    val d = Day4()
    d.run()
}

class Day4 {
    fun run() {
        val input = Day4::class.java.getResource("/seventeen/day4.txt").readText()
        val list = input.split("\r\n")
        println("Part 1")
        println(part1(list))

        println("Part 2")
        println(part2(list))
    }

    fun part1(input: List<String>): Number {
        return input.map { phrase -> isValid(phrase.split(" ")) }.filter { it }.size
    }

    fun isValid(input: List<String>): Boolean {
        return !input.groupingBy { it }.eachCount().any { it.value > 1 }
    }

    fun isValidAnagrams(input: List<String>): Boolean {
        return input.map { it.toList().sorted().joinToString("") }.groupingBy { it }.eachCount().all { it.value == 1 }
    }

    fun part2(input: List<String>): Number {
        return input.map { phrase -> isValidAnagrams(phrase.split(" ")) }.filter { it }.size
    }

}