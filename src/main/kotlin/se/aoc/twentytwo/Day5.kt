package se.aoc.twentytwo


fun main() {
    val d = Day5()
    d.run()
}

class Day5 {
    /*
       [C]         [Q]         [V]
       [D]         [D] [S]     [M] [Z]
       [G]     [P] [W] [M]     [C] [G]
       [F]     [Z] [C] [D] [P] [S] [W]
   [P] [L]     [C] [V] [W] [W] [H] [L]
   [G] [B] [V] [R] [L] [N] [G] [P] [F]
   [R] [T] [S] [S] [S] [T] [D] [L] [P]
   [N] [J] [M] [L] [P] [C] [H] [Z] [R]
    1   2   3   4   5   6   7   8   9
     */

    lateinit var instructions: List<Instruction>
    lateinit var stacks: MutableMap<Int, MutableList<Char>>

    fun run() {
        val input = Day5::class.java.getResource("/twentytwo/day5.txt")!!.readText()
        instructions = createInstructions(input)
        stacks = createStacks()

        println("Part 1")
        println(part1())

        instructions = createInstructions(input)
        stacks = createStacks()

        println("Part 2")
        println(part2())
    }

    private fun createStacks(): MutableMap<Int, MutableList<Char>> {
        val stacks = mutableMapOf<Int, MutableList<Char>>()
        stacks[1] = mutableListOf('N', 'R', 'G', 'P')
        stacks[2] = mutableListOf('J', 'T', 'B', 'L', 'F', 'G', 'D', 'C')
        stacks[3] = mutableListOf('M', 'S', 'V')
        stacks[4] = mutableListOf('L', 'S', 'R', 'C', 'Z', 'P')
        stacks[5] = mutableListOf('P', 'S', 'L', 'V', 'C', 'W', 'D', 'Q')
        stacks[6] = mutableListOf('C', 'T', 'N', 'W', 'D', 'M', 'S')
        stacks[7] = mutableListOf('H', 'D', 'G', 'W', 'P')
        stacks[8] = mutableListOf('Z', 'L', 'P', 'H', 'S', 'C', 'M', 'V')
        stacks[9] = mutableListOf('R', 'P', 'F', 'L', 'W', 'G', 'Z')
        return stacks
    }

    fun createInstructions(str: String): List<Instruction> {
        val list = str.split(System.lineSeparator())
        val l = list.filter { it.startsWith("move") }.map {
            val l = it.split(" ")
            Instruction(l[1].toInt(), l[3].toInt(), l[5].toInt())
        }
        return l
    }

    fun part1(): String {
        doInstructions(true)
        return stacks.map { it.value.last() }.joinToString(separator = "")
    }

    fun part2(): String {
        doInstructions(false)
        return stacks.map { it.value.last() }.joinToString(separator = "")
    }

    private fun doInstructions(doReversed: Boolean) {
        instructions.forEach {
            val a = stacks[it.from]!!.size - it.stack
            val b = stacks[it.from]!!.size
            val crates = stacks[it.from]!!.subList(a, b)
            if (doReversed) {
                stacks[it.to]!!.addAll(crates.reversed())
            } else {
                stacks[it.to]!!.addAll(crates)
            }
            stacks[it.from] = stacks[it.from]!!.subList(0, a)
        }
    }
}

data class Instruction(val stack: Int, val from: Int, val to: Int)

