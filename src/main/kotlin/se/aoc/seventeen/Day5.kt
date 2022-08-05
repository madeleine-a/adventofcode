package se.aoc.seventeen

fun main() {
    val d = Day5()
    d.run()
}

class Day5 {
    fun run() {
        val input = Day5::class.java.getResource("/seventeen/day5.txt").readText()
        val list = input.split("\r\n").map { it.toInt() }.toList()
        println("Part 1")
        println(part1(list))

        println("Part 2")
        println(part2(list))
    }

    fun part1(input: List<Int>): Number {
        var cont = true
        var count = 0
        var jump = input[0]
        var list = input.toMutableList()
        while (cont) {
            jump = makeJump(jump, list)
            if (jump >= list.size || jump < 0) {
                cont = false
            }
            count++
        }
        return count
    }

    private fun makeJump(jump: Int, input: MutableList<Int>): Int {
        val value = input[jump]
        input[jump] = value + 1
        return value + jump
    }

    private fun makeJumpV2(jump: Int, input: MutableList<Int>): Int {
        val value = input[jump]
        if (value >= 3) {
            input[jump] = value - 1
        } else {
            input[jump] = value + 1
        }
        return value + jump
    }

    fun part2(input: List<Int>): Number {
        var cont = true
        var count = 0
        var jump = input[0]
        var list = input.toMutableList()
        while (cont) {
            jump = makeJumpV2(jump, list)
            if (jump >= list.size || jump < 0) {
                cont = false
            }
            count++
        }
        return count
    }

}