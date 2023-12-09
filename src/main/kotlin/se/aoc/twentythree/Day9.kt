package se.aoc.twentythree

import splitStrOfNumbersToList

fun main() {
    val d = Day9()
    d.run()
}

class Day9 {

    fun run() {
        val input = Day9::class.java.getResource("/twentythree/day9.txt").readText()
        val list = createList(input)
        println("Part 1")
        println(part1(list)) // Expeced: 54331

        println("Part 2")
        println(part2(list)) // 54518
    }

    fun createList(str: String): Set<List<Long>> {
        return str.lines().map { s -> s.splitStrOfNumbersToList() }.toSet()
    }

    fun part1(input: Set<List<Long>>): Number {
        val res = input.map { list ->
            runList(list, list.last())
        }
       return res.sum()
    }

    private fun runList(l: List<Long>, last: Long): Long {
        val res = l.zipWithNext().map { c -> c.second-c.first }
        return if(res.sum()!=0L) runList(res, res.last()+last) else{
            last
        }
    }

    fun part2(input: Set<List<Long>>): Number {
        val reversed = input.map { it.reversed() }
        val res = reversed.map { list ->
            runList(list, list.last())
        }
        return res.sum()
    }
}
