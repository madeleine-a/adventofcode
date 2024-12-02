package se.aoc.twentyfour

import kotlin.math.absoluteValue


fun main() {
    val d = Day2()
    d.run()
}

class Day2 {
    fun run() {
        val input = Day2::class.java.getResource("/twentyfour/day2.txt").readText()
        val list = createList(input)
        println("Part 1")
        println(part1(list)) // Expeced: 54331

        println("Part 2")
        println(part2(list)) // 54518
    }

    private fun isSafe(list: List<Int>): Boolean {
        return isIncreasing(list).map { it.second }.reduce{ a,b -> b && a } ||
                isDecreasing(list).map{it.second }.reduce{ a,b -> b && a }
    }

    private fun isSafeWithProblemDamper(list: List<Int>): Boolean {
        val incResult = isIncreasing(list)
        val decResult = isDecreasing(list)
        val inc = incResult.map { it.second }.reduce{ a,b -> b && a }
        val dec = decResult.map{it.second }.reduce{ a,b -> b && a }
        if(inc || dec){
            return true
        } else {
            for (i in list.indices){
                val newList = list.mapIndexedNotNull{ index, value -> if(index == i) null else value }

                if(isIncreasing(newList).map { it.second }.reduce{ a,b -> b && a } ||
                        isDecreasing(newList).map{it.second }.reduce{ a,b -> b && a }){
                    return true
                }
            }
            return false
        }
    }


    private fun isIncreasing(list: List<Int>): List<Pair<String, Boolean>> {
        val result = list.subList(0, list.size - 1).mapIndexed { index, i ->
            val value = (i - list[index + 1]).absoluteValue
            if (i <= list[index + 1]) {
                "NOT" to false
            } else if (value < 1 || value > 3) {
                "DIFF" to false
            } else {
                "OK" to true
            }
        }
        return result
    }

    private fun isDecreasing(list: List<Int>): List<Pair<String, Boolean>> {
        val result = list.subList(0, list.size - 1).mapIndexed { index, i ->
            val value = (i - list[index + 1]).absoluteValue
            if (i >= list[index + 1]) {
                "NOT" to false
            } else if (value < 1 || value > 3) {
                "DIFF" to false
            } else {
                "OK" to true
            }
        }
       return result
    }

    fun createList(str: String): List<List<Int>> {
        val list = str.split("\n").map {
            it.split(" ").map { it.toInt() }
        }
        return list

    }

    fun part1(list: List<List<Int>>): Number {
        val result = list.map { it to isSafe(it) }.filter { it.second }
        return result.size
    }

    fun part2(list: List<List<Int>>): Number {
        val result = list.map { it to isSafeWithProblemDamper(it) }.filter { it.second }
        return result.size
    }
}
