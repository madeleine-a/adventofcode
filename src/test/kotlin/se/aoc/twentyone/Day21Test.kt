package se.aoc.twentyone

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day21Test {
val day = Day21()
    @Test
    fun run() {
        day.run()
    }

    @Test
    fun part1() {
        assertEquals(739785, day.part1(Player(1, 4, ), Player(2, 8)))
    }

    @Test
    fun part2() {
        day.part2(Player(1, 4, ), Player(2, 8))
    }

    @Test
    fun possibleSums(){
        for(i in 1..3){
            for(j in 1..3){
                for(k in 1..3){
                    println(i+j+k)
                }
            }
        }
    }
}