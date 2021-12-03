package se.aoc.twentyone

class Day3 {
    fun run() {
        val input = Day3::class.java.getResource("/twentyone/day3.txt").readText()
        val list = input.split("\r\n")
        println("Part 1")
        println(part1(list))

        println("Part 2")
        println(part2(list))
    }

    fun part1(input: List<String>): Number {
        return input.columnsToRows().bitCount().map { list -> list.map { pair -> pair.first.toString() } }
            .reduce { x, y -> x.zip(y, String::plus) }.map { b -> b.toInt(2) }.reduce { x, y -> x * y }
    }

    fun part2(input: List<String>): Number {
        var scrubber = mutableListOf<String>()
        scrubber.addAll(input)
        var oxygen  = mutableListOf<String>()
        oxygen.addAll(input)
        for (i in 0 until input.first().length) {
            if(oxygen.size > 1){
                oxygen  = oxygen.filterListOnHighest(i)
            }
            if(scrubber.size > 1) {
                scrubber = scrubber.filterListOnLowest(i)
            }
        }
        return oxygen.first().toInt(2) * scrubber.first().toInt(2)
    }

}

fun List<String>.filterListOnHighest(pos: Int): MutableList<String> {
    val bitCount = this.columnsToRows().bitCount()
    if (bitCount[pos].size > 1 && bitCount[pos].first().second == bitCount[pos].last().second) {
        return this.filter { s -> s[pos] == '1' }.toMutableList()
    }
    return this.filter { s -> s[pos] == bitCount[pos].first().first }.toMutableList()
}

fun List<String>.filterListOnLowest(pos: Int): MutableList<String> {
    val bitCount = this.columnsToRows().bitCount()
    if (bitCount[pos].size > 1 && bitCount[pos].first().second == bitCount[pos].last().second) {
        return this.filter { s -> s[pos] == '0' }.toMutableList()
    }
    return this.filter { s -> s[pos] == bitCount[pos].last().first }.toMutableList()
}


private fun List<String>.bitCount(): List<List<Pair<Char, Int>>> {
    val res = this.map { str ->
        str.toList().groupBy { it }.map { it.key to it.value.size }
            .sortedByDescending { p -> p.second }
    }
    return res
}

private fun List<String>.columnsToRows(): List<String> {
    return this.map { it.toList().map { c -> c.toString() } }.reduce { x, y -> x.zip(y, String::plus) }
}