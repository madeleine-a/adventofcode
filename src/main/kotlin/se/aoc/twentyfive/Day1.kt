package se.aoc.twentyfive

import kotlin.math.absoluteValue


fun main() {
    val d = Day1()
    d.run()
}

data class Rotation(val direction: Direction, val steps: Int) {
    val amount: Int
        get() = when (direction) {
            Direction.LEFT -> -steps
            Direction.RIGHT -> steps
        }
}

enum class Direction(val str: String) {
    LEFT("L"), RIGHT("R");

    companion object {
        fun fromChar(c: Char): Direction {
            return when (c) {
                'L' -> LEFT
                'R' -> RIGHT
                else -> throw IllegalArgumentException("Unknown direction: $c")
            }
        }
    }
}

class Day1 {
    fun run() {
        val input = Day1::class.java.getResource("/twentyfive/1.txt").readText()
        val list = createList(input)
        println("Part 1")
        println(part1(list))

        println("Part 2")
        println(part2(list))
    }

    fun createList(str: String): List<Rotation> {
        val list = str.lines().map {
            val direction = Direction.fromChar(it[0])
            val steps = it.substring(1).toInt()
            Rotation(direction, steps)
        }
        return list
    }

    fun part1(rotations: List<Rotation>): Number {
        return runRotations(rotations).filter { it.first == 0 }.size
    }

    fun part2(rotations: List<Rotation>): Number {
        return runRotations(rotations).sumOf { pair -> pair.second  }
    }

    private fun runRotations(rotations: List<Rotation>) =
        rotations.runningFold(50 to 0) { posAndOverZero, rotation ->
            val res = posAndOverZero.first + rotation.amount
            res % 100 to getNumberOfTimesPassingZero(posAndOverZero.first, res)
        }


    private fun getNumberOfTimesPassingZero(from: Int, to: Int): Int {
        val no = if (from > 0 && to < 0) 1 else if (from < 0 && to > 0) 1 else 0
        val hits = to.absoluteValue / 100
        val onZero = if (to == 0) 1 else 0
        return no + hits + onZero
    }
}
