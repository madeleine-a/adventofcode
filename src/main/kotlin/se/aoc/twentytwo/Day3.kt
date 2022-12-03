package se.aoc.twentytwo


fun main() {
    val d = Day3()
    d.run()
}

class Day3 {
    lateinit var priority: Map<Char, Int>
    fun run() {
        val input = Day3::class.java.getResource("/twentytwo/day3.txt").readText()
        val list = createList(input)
        println("Day 3")
        println("Part 1")
        println(part1(list))

        println("Part 2")
        println(part2(list))
    }

    fun createList(str: String): List<List<Char>> {
        val value = CharRange('a', 'z').toMutableList()
        value.addAll(CharRange('A', 'Z').toList())
        priority = value.withIndex().map { it.value to it.index + 1 }.toMap()

        return str.split(System.lineSeparator()).map { it.toList() }
    }

    fun part1(input: List<List<Char>>): Number {
        return input.map { r -> r.chunked(r.size / 2) }.map { it.first() to it.last() }.map { r ->
            r.first.intersect(r.second.toSet())
        }.map { it.first() }.sumOf { c -> priority[c]!! }
    }

    fun part2(input: List<List<Char>>): Number {
        val groups = input.windowed(3, 3).map { it.map { it.toMutableSet() } }

        return groups.map { list ->
            list.reduce { acc, it -> acc.apply { retainAll(it) } }
        }.flatten().sumOf { c -> priority[c]!! }

    }


}


