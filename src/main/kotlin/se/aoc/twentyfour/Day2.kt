package se.aoc.twentyfour

import se.aoc.takeTime
import kotlin.math.absoluteValue


fun main() {
    val d = Day2()
    d.run()
}

class Day2 {
    fun run() {
        val input = Day2::class.java.getResource("/twentyfour/day2.txt").readText()

        val (list, time) = takeTime { createSequence(input) }
        println("Time to create list: $time")

        val (part1Result, time1) = takeTime { part1(list) }
        println("Time to part1: $time1")
        println("Part 1")
        println(part1Result)

        val (part2Result, time2) = takeTime { part2(list) }
        println("Time to part2: $time2")
        println("Part 2")
        println(part2Result)
    }


    fun createSequence(str: String): Sequence<Sequence<Int>> {
        val list = str.split("\n").asSequence().map {
            it.split(" ").map { it.toInt() }.asSequence()
        }
        return list
    }

    fun createList(str: String): List<List<Int>> {
        val list = str.split("\n").map {
            it.split(" ").map { it.toInt() }
        }
        return list
    }

    fun part1(sequence: Sequence<Sequence<Int>> ): Number {
        val result = sequence.map { it to isSafe(it) }.filter { it.second }
        return result.count()
    }


    fun part2(list: Sequence<Sequence<Int>> ): Number {
        val result = list.map { it to isSafeWithProblemDamper(it) }.filter { it.second }
        return result.count()
    }




    private fun isSafe(seq: Sequence<Int>): Boolean {
        return checkList(seq, true).map { it.second }.reduce { a, b -> b && a } ||
                checkList(seq, false).map { it.second }.reduce { a, b -> b && a }
    }



    private fun isSafeWithProblemDamper(seq: Sequence<Int>): Boolean {
        if (isSafe(seq)) {
            return true
        } else {
            seq.forEachIndexed { i,_ ->
                val newSequence = seq.filterIndexed { index, value -> index != i }
                if (isSafe(newSequence)) {
                    return true

                }
            }
            return false
        }
    }


    private fun checkList(seq: Sequence<Int>, doUp: Boolean): List<Pair<String, Boolean>> {
        val result = seq.take(seq.count() - 1)
            .mapIndexed { index, i ->
                val value = (i - seq.elementAt(index + 1).absoluteValue)
                if ((doUp && i <= value) || (!doUp && i >= value)) {
                    "NOT" to false
                } else if (value < 1 || value > 3) {
                    "DIFF" to false
                } else {
                    "OK" to true
                }
            }

        return result.toList()
    }

}
