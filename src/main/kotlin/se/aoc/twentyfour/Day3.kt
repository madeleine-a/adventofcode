package se.aoc.twentyfour


fun main() {
    val d = Day3()
    d.run()
}

class Day3 {
    fun run() {
        val input = Day3::class.java.getResource("/twentyfour/day3.txt").readText()
        val list = parseString(input)
        println("Part 1")
        println(part1(list)) // Expeced: 168539636

        println("Part 2")
        println(part2(list)) // Expeced: 97529391
    }

    fun parseString(str: String): Sequence<MatchResult> {
        val regex = Regex("""mul\(\d+,\d+\)|do\(\)|don't\(\)""")
        return regex.findAll(str)
    }

    fun runInstruction(str: String): Int {
        val regex = Regex("""mul\((\d+),(\d+)\)""")
        val matchResult = regex.find(str)
        if (matchResult == null) return 0
        val (a, b) = matchResult.destructured
        return a.toInt() * b.toInt()
    }


    fun part1(sequence: Sequence<MatchResult>): Number {
        return sequence.map { runInstruction(it.value) }.sum()
    }

    fun part2(sequence: Sequence<MatchResult>): Number {
        var skip = false
        return sequence.map { instr ->
            if (instr.value == "don't()") {
                skip = true
                0
            } else if (instr.value == "do()") {
                skip = false
                0
            } else if (!skip) {
                runInstruction(instr.value)
            } else {
                0
            }

        }.sum()
    }

}
