package se.aoc.twentyfour

import se.aoc.twentythree.isNumber


fun main() {
    val d = Day5()
    d.run()
}

class Day5 {
    fun run() {
        val input = Day5::class.java.getResource("/twentyfour/day5.txt").readText()
        val data = parseString(input)
        println("Part 1")
        println(part1(data.first, data.second)) // Expeced: 168539636

        println("Part 2")
        println(part2(data.first, data.second)) // Expeced: 97529391
    }

    fun parseString(str: String): Pair<Sequence<Pair<Int, Int>>, Sequence<Sequence<Int>>> {
        val instructions = mutableListOf<Pair<Int, Int>>()
        val sections = mutableListOf<Sequence<Int>>()
        val result = str.lines().map { line ->
            if (isInstruction(line)) {
                val inst = line.split("|")
                instructions.add(inst.first().toInt() to inst.last().toInt())

            } else if (!line.isBlank()) {
                sections.add(line.split(",").map { it.toInt() }.asSequence())
            } else {
                // nothing
            }
        }

        return instructions.asSequence() to sections.asSequence()

    }

    private fun isInstruction(str: String) = str.contains("|")


    fun part1(instructions: Sequence<Pair<Int, Int>>, sections: Sequence<Sequence<Int>>): Number {
        val result = sections.map { section ->
           section.mapIndexed {  index, page ->
               println(page)
               val nextPages = section.drop(index+1)
               val lastPages = section.take(index)
               println(nextPages.toList())
               println(lastPages.toList())
               val inst = findInstructions(page, instructions)
               lastPages.map { p ->
               }
               nextPages.map { p ->
                   (inst.map { it.second }.contains(p))
               }
           }
        }
        println(result.toList())
        return 0
    }


    private fun findInstructions(page: Int, instructions: Sequence<Pair<Int, Int>>): Sequence<Pair<Int, Int>> {
        return instructions.filter { it.first == page || it.second == page }
    }

    fun part2(instructions: Sequence<Pair<Int, Int>>, sections: Sequence<Sequence<Int>>): Number {
        return 0
    }

}
