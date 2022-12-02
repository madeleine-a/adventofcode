package se.aoc.twentytwo

import se.aoc.twentytwo.Result.*
import se.aoc.twentytwo.Shape.*


fun main() {
    val d = Day2()
    d.run()
}

class Day2 {
    fun run() {
        val input = Day1::class.java.getResource("/twentytwo/day2.txt").readText()
        val list = createList(input)
        println("Day 2")
        println("Part 1")
        println(part1(list))

        println("Part 2")
        println(part2(list))
    }

    fun createList(str: String): List<Pair<String, String>> {
        return str.split(System.lineSeparator()).map { s -> s.split(" ") }.map { it.first() to it.last() }
    }

    fun part1(rounds: List<Pair<String, String>>): Number {
        return rounds.sumOf { calculateScore(it) + getWinOrLoose(it)!!.value }
    }

    fun part2(rounds: List<Pair<String, String>>): Number {
       return rounds.sumOf { r ->
            val o = getShape(r.first)
            val y = when(getExpectedResult(r.second)){
                WIN -> {
                    when(o){
                        ROCK -> PAPER
                        SCISSORS -> ROCK
                        PAPER -> SCISSORS
                        else -> null
                    }
                }
                LOOSE -> {
                    when(o){
                        ROCK -> SCISSORS
                        SCISSORS -> PAPER
                        PAPER -> ROCK
                        else -> null
                    }
                }
                DRAW -> o
                else -> null
            }
           y!!.value + getExpectedResult(r.second)!!.value
        }
    }


    private fun getWinOrLoose(round: Pair<String, String>): Result? {
        val opponent = getShape(round.first)
        val you = getShape(round.second)
        return when(you){
            ROCK -> {
                when(opponent){
                    ROCK -> DRAW
                    SCISSORS -> WIN
                    PAPER -> LOOSE
                    else -> null
                }
            }
            SCISSORS -> {
                when(opponent){
                    ROCK -> LOOSE
                    SCISSORS -> DRAW
                    PAPER -> WIN
                    else -> null
                }
            }
            PAPER -> {
                when(opponent){
                    ROCK -> WIN
                    SCISSORS -> LOOSE
                    PAPER -> DRAW
                    else -> null
                }
            }
            else -> null
        }
    }

    private fun calculateScore(round: Pair<String, String>): Int {
        return getShape(round.second)!!.value
    }

    private fun getShape(str: String): Shape? {
        return when (str) {
            "A", "X" -> ROCK
            "B", "Y" -> PAPER
            "C", "Z" -> SCISSORS
            else -> {
                null
            }
        }
    }

    private fun getExpectedResult(str: String): Result? {
        return when (str) {
            "X" -> LOOSE
            "Y" -> DRAW
            "Z" -> WIN
            else -> {
                null
            }
        }
    }


}

enum class Result(val value: Int) {
    WIN(6), LOOSE(0), DRAW(3)
}

enum class Shape(val value: Int) {
    ROCK(1), PAPER(2), SCISSORS(3)
}

