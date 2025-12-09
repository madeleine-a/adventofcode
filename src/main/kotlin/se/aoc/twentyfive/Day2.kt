package se.aoc.twentyfive


fun main() {
    val d = Day2()
    d.run()
}


class Day2 {
    fun run() {
        val input = Day2::class.java.getResource("/twentyfive/2.txt").readText()
        val list = createList(input)
        println("Part 1")
        println(part1(list))

        println("Part 2")
        println(part2(list))
    }

    fun createList(str: String): List<Pair<Long, Long>> {
        val list = str.split(",").map {
            val l = it.split("-")
            l.first().toLong() to l.last().toLong()
        }
        return list
    }

    fun part1(list: List<Pair<Long, Long>>): Long {
        val res = list.map {
            val seq = (it.first..it.second).asSequence()
            seq.map { it ->
                val x = splitInHalves(it.toString())
                if (x.first == x.second) it else 0L
            }.sumOf { it }
        }
        return res.sum()
    }

    fun part2(list: List<Pair<Long, Long>>): Long {
        val res = list.map {
            val seq = (it.first..it.second).asSequence()
            seq.map {                 if(hasValueRepeatablePatterns(it.toString())) it else 0L
            }.sumOf { value -> value }
        }
        return res.sum()
    }

    private fun splitInHalves(str: String): Pair<String, String> {
        val mid = str.length / 2
        return str.take(mid) to str.substring(mid)
    }

    fun hasValueRepeatablePatterns(str: String): Boolean {
        if(str.length == 1) return false
        val mid = str.length / 2
        return(1..mid).map { it ->
            val list = str.chunked(it)
            val no = countValue(list.first(), list)
            (no == list.size)
        }.reduce { a, b -> a || b }
    }

    private fun countValue(value: String, list: List<String>): Int {
        return list.count { it == value }
    }
}
