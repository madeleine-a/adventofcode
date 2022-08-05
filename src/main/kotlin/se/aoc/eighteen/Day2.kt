package se.aoc.eighteen

class Day2 {
    fun run() {
        val input = Day2::class.java.getResource("/eighteen/day2.txt").readText()
        val list = input.split("\r\n")
        println("Part 1")
        println(part1(list))
        println("Part 2")
        println(part2(list))
    }

    fun part1(input: List<String>): Number {
        return input.map {
            it.toCharArray().groupBy { c -> c }
                .map { c -> c.key to c.value.size }.filter { p -> p.second == 2 || p.second == 3 }
                .map { p -> p.second }.distinct()
        }.flatten().groupingBy { it }.eachCount().values.reduce { x, y -> x * y }
    }

    fun part2(input: List<String>): String {
        val list = input.map { it.toCharArray().map { c -> c.toString() } }
        var map: MutableMap<List<String>, Map<List<String>, Int>> = mutableMapOf()

        list.forEach { x -> list.forEach{ y -> countDiff(x, y)} }
        for (i in list.indices) {
            var diffList: MutableMap<List<String>, Int> = mutableMapOf()
            for (j in list.indices) {
                if (i != j) {
                    diffList[list[j]] = countDiff(list[i], list[j])
                }
            }
            map[list[i]] = diffList.filter { (_, v) -> v == 1 }
        }
        map = map.filter { (_, v) -> v.isNotEmpty() } as MutableMap<List<String>, Map<List<String>, Int>>
        val what = map.keys.first().zip(
            map.values.first().keys
                .first()
        ).filter { (x, y) -> x == y }.map { (x, y) -> x }.joinToString(separator = "")
        return what
    }

    private fun countDiff(a: List<String>, b: List<String>): Int {
        return a.zip(b).filter { (x, y) -> x != y }.toList().count()
    }
}