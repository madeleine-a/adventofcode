package se.aoc.twentytwo

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day13Test {
    val day = Day13()
    val input = Day13::class.java.getResource("/twentytwo/day13test.txt").readText()

    @Test
    fun part1() {
        day.createList(input)
        assertEquals(13, day.part1())
    }

    @Test
    fun part2() {
        day.createList(input)

        assertEquals(140, day.part2())
    }

    @Test
    fun compare() {
        val a = "[1,[2,[3,[4,[5,6,7]]]],8,9]".replace("[", "[,").replace("]", ",]").split(",").filter { it.isNotEmpty() }
        val b = "[1,[2,[3,[4,[5,6,0]]]],8,9]".replace("[", "[,").replace("]", ",]").split(",").filter { it.isNotEmpty() }
        assertFalse(day.compare(day.charsToListValue(a), day.charsToListValue(b)))
    }



    @Test
    fun compare1() {
        val a = "[[[]]]".replace("[", "[,").replace("]", ",]").split(",").filter { it.isNotEmpty() }
        val b = "[[]]".replace("[", "[,").replace("]", ",]").split(",").filter { it.isNotEmpty() }
        assertFalse(day.compare(day.charsToListValue(a), day.charsToListValue(b)))
    }

    @Test
    fun compare2() {
        val a = "[[]]".replace("[", "[,").replace("]", ",]").split(",").filter { it.isNotEmpty() }
        val b = "[[[]]]".replace("[", "[,").replace("]", ",]").split(",").filter { it.isNotEmpty() }
        assertTrue(day.compare(day.charsToListValue(a), day.charsToListValue(b)))
    }

    @Test
    fun compare3() {
        val a = "[[4,3,4,[2]]]".replace("[", "[,").replace("]", ",]").split(",").filter { it.isNotEmpty() }
        val b = "[[],[6,7,1,0],[0,9],[3,[0],2],[4,[[2,7]]]]".replace("[", "[,").replace("]", ",]").split(",").filter { it.isNotEmpty() }
        assertFalse(day.compare(day.charsToListValue(a), day.charsToListValue(b)))
    }

    @Test
    fun charsToListValue() {

        val b = "[[],[6,7,1,0],[0,9],[3,[0],2],[4,[[2,7]]]]".replace("[", "[,").replace("]", ",]").split(",").filter { it.isNotEmpty() }
        val list = day.charsToListValue(b)
        println(list)
    }

    @Test
    fun compare4() {
        val a = "[9]".replace("[", "[,").replace("]", ",]").split(",").filter { it.isNotEmpty() }
        val b = "[[8,7,6]]".replace("[", "[,").replace("]", ",]").split(",").filter { it.isNotEmpty() }
        assertFalse(day.compare(day.charsToListValue(a), day.charsToListValue(b)))
    }

    @Test
    fun compare5() {
        val a = "[[1],[2,3,4]]".replace("[", "[,").replace("]", ",]").split(",").filter { it.isNotEmpty() }
        val b = "[[1],4]".replace("[", "[,").replace("]", ",]").split(",").filter { it.isNotEmpty() }
        assertTrue(day.compare(day.charsToListValue(a), day.charsToListValue(b)))
    }

    @Test
    fun compare6() {
        val a = "[[7,1],[9,5,8,10],[[[5,1,0,10],[10,9,2,5]],4,5,2,2]]".replace("[", "[,").replace("]", ",]").split(",").filter { it.isNotEmpty() }
        val b = "[[9],[1,8,3],[],[9,[1,3,0,[10]]]]".replace("[", "[,").replace("]", ",]").split(",").filter { it.isNotEmpty() }
        val bList = day.charsToListValue(b)
        val aList = day.charsToListValue(a)
        val list = listOf(bList, aList, bList)
        val sorted = list.sorted()

     sorted.forEach { println(it) }
        assertTrue(day.compare(day.charsToListValue(a), day.charsToListValue(b)))
    }

    @Test
    fun orderLists(){
        val a = listOf(1,2,3,4,5)
        val b = listOf(1,2,3,4,4)
        val c = listOf(3)
        val d = listOf(3, 3)
        val list = listOf(a, b, c, c)
    }







}

