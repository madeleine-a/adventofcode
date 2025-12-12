package se.aoc.twentyfive

import se.aoc.push

fun main() {
    val d = Day5()
    d.run()
}


class Day5 {
    fun run() {
        val input = Day5::class.java.getResource("/twentyfive/5.txt").readText()
        val list = createList(input)
        println("Part 1")
        println(part1(list.first, list.second))

        println("Part 2")
        println(part2(list.first))
    }


    fun createList(str: String): Pair<List<Pair<Long, Long>>, List<Long>> {
        val list = str.split(System.lineSeparator().repeat(2))
        val ranges = list.first().lines().map {
            val l = it.split("-")
            (l[0].toLong() to l[1].toLong())
        }
        val ids = list.last().lines().map { it.toLong() }
        return ranges to ids
    }

    fun part1(ranges: List<Pair<Long, Long>>, ids: List<Long>): Long {
        return ids.count { id ->
            var found = false
            for (range in ranges) {
                if (range.first <= id && range.second >= id) {
                    found = true
                    break
                }
            }
            found
        }.toLong()


    }


    fun part2(ranges: List<Pair<Long, Long>>): Long {

        var size = ranges.size
        var doContinue = true
        var reduced = ranges

        while (doContinue) {
            reduced = reduceRanges(reduced.sortedBy { it.first })
            if (reduced.size != size) {
                size = reduced.size
            } else {
                doContinue = false
            }
        }
        return reduced.sumOf { range ->
            println("${range.first} - ${range.second}" )
            range.second - range.first + 1
        }
    }

    private fun reduceRanges(ranges: List<Pair<Long, Long>>): List<Pair<Long, Long>> {
        val stack = mutableListOf<Pair<Long, Long>>()
        stack.push(ranges.first())
        ranges.drop(1)
            .forEach { range ->
                stack.map {
                    if(range.first > it.first && range.first < it.second &&
                        range.second > it.first && range.second < it.second){
                        //Contained
                        return@forEach
                    }                         
                    else if (range.first <= it.first && range.second >= it.second) {
                        stack.remove(it)
                        stack.push(range.first to range.second)
                        return@forEach
                    } else if (range.first <= it.first &&
                        range.second in it.first..it.second
                    ) {
                        stack.remove(it)
                        stack.push(range.first to it.second)
                        return@forEach
                    } else if (range.second >= it.second &&
                        range.first in it.first..it.second
                    ) {
                        stack.remove(it)
                        stack.push(it.first to range.second)
                        return@forEach
                    } else if(range.first == it.second+1){
                        stack.remove(it)
                        stack.push(it.first to range.second)
                        return@forEach
                    } else if(range.second == it.first-1){
                        stack.remove(it)
                        stack.push(range.first to it.second)
                        return@forEach
                    } else if(range.first == it.second+1){
                        stack.remove(it)
                        stack.push(it.first to range.second)
                        return@forEach
                    }
                }
                stack.push(range)
            }
        return stack
    }
}

