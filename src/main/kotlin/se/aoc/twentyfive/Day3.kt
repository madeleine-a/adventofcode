package se.aoc.twentyfive


fun main() {
    val d = Day3()
    d.run()
}


class Day3 {
    fun run() {
        val input = Day3::class.java.getResource("/twentyfive/3.txt").readText()
        val list = createList(input)
        println("Part 1")
        println(part1(list))

        println("Part 2")
        println(part2(list))
    }

    fun createList(str: String): List<List<Char>> {
        return str.lines().map { it.toList() }
    }

    fun part1(list: List<List<Char>>): Long {
        return list.map { bank ->
            val first = bank.asSequence().take(bank.size - 1)
                .reduce { acc, c -> if (acc.digitToInt() > c.digitToInt()) acc else c }
            val firstIndex = bank.mapIndexed { index, c ->
                c to index
            }.first { (c, _) -> c == first }.second
            val second =
                bank.drop(firstIndex + 1).reduce { acc, c -> if (acc.digitToInt() > c.digitToInt()) acc else c }
            (first.toString() + second.toString()).toLong()
        }.sumOf { it }
    }

    fun part2(list: List<List<Char>>): Long {
        return list.map { bank ->
            var noOfDigits = 11
            var res = ""
            var drop = 0
            while (noOfDigits >= 0) {
                val pair =  getHighestJoltageOutputAndIndex(
                    bank.dropLast(noOfDigits).drop(drop),
                )
                res += pair.first.toString()
                drop += pair.second + 1
                noOfDigits--
            }
            res.toLong()
        }.sumOf { it }
    }

    fun getHighestJoltageOutputAndIndex(list: List<Char>): Pair<Char, Int> {
        val output = list.asSequence().take(list.size)
            .reduce { acc, c -> if (acc.digitToInt() > c.digitToInt()) acc else c }
        val firstIndex = list.mapIndexed { index, c ->
            c to index
        }.first { (c, _) -> c == output }.second
        return output to firstIndex
    }
}
