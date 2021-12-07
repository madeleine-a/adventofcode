package se.aoc.twentyone

import kotlin.math.absoluteValue

class Day7 {

    fun run() {
        val input = Day7::class.java.getResource("/twentyone/day7.txt").readText()
        val list = input.split(",").map { it.toInt() }
        println("Part 1")
        println(part1(list))

        println("Part 2")
        println(part2(list))
    }


    fun part1(list: List<Int>): Number {
        var min = list.minOrNull()?:0
        var max = list.maxOrNull()?:0
        var result = mutableListOf<Pair<Int, Int>>()

        for(i in min..max){
            val sum = list.sumOf {  l -> (l - i).absoluteValue }
            result.add(i to sum)
        }
        return result.minByOrNull { (key, value) -> value }!!.second

        return list.map { it to list.sumOf { l -> (l - it).absoluteValue } }.minByOrNull { (key, value) -> value }!!.second
    }


    fun part2(list: List<Int>): Number {
        var min = list.minOrNull()?:0
        var max = list.maxOrNull()?:0
        var result = mutableListOf<Pair<Int, Int>>()

        for(i in min..max){
            val sum = list.sumOf {  l ->
                val v = (l - i).absoluteValue
                v * (v+1)/2 }
            result.add(i to sum)
        }
        return result.minByOrNull { (key, value) -> value }!!.second
    }

}


