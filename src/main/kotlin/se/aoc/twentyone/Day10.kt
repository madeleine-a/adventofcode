package se.aoc.twentyone

import se.aoc.Stack
import se.aoc.peek
import se.aoc.pop
import se.aoc.push

class Day10 {
    val map = mapOf(')' to '(', ']' to '[', '>' to '<', '}' to '{')
    val map2 = mapOf('(' to ')', '[' to ']', '<' to '>', '{' to '}')
    val points = mapOf(')' to 3, ']' to 57, '}' to 1197, '>' to 25137)
    val pointsPart2 = mapOf(')' to 1, ']' to 2, '}' to 3, '>' to 4)


    fun run() {
        val input = Day10::class.java.getResource("/twentyone/day10.txt").readText()
        val list = input.split("\r\n")

        println("Part 1")
        println(part1(list))

        println("Part 2")
        println(part2(list))
    }

    fun isACloser(c: Char): Boolean {
        return map.keys.contains(c)
    }

    fun isAnOpener(c: Char): Boolean {
        return map.values.contains(c)
    }

    fun part1(list: List<String>): Number {
        val broken = mutableListOf<Char>()
        list.forEach str@{ s ->
            val charArr = s.toList()
            val stack: Stack<Char> = mutableListOf()
            charArr.forEach char@{ c ->
                if (isAnOpener(c)) {
                    stack.push(c)
                } else {
                    if (stack.size > 0 && isACloser(c) && stack.peek() == map[c]) {
                        stack.pop()
                    } else {
                        broken.add(c)
                        return@str
                    }
                }
            }
        }
        return broken.sumOf { c -> points[c] ?: 0 }
    }

    fun part2(list: List<String>): Number {
        val broken = mutableListOf<String>()
        val incomplete = list.map str@{ s ->
            val charArr = s.toList()
            val stack: Stack<Char> = mutableListOf()
            charArr.forEach char@{ c ->
                if (isAnOpener(c)) {
                    stack.push(c)
                } else {
                    if (stack.size > 0 && isACloser(c) && stack.peek() == map[c]) {
                        stack.pop()
                    } else {
                        return@str ""
                    }
                }
            }
            stack.joinToString("")
        }.filter { it.isNotEmpty() }


        val r = incomplete.map { s ->
            calcPartToStr(s.toList().map { c -> map2[c] }.toList().reversed().joinToString(""))
        }
        val index = r.size/2
        return r.sorted()[index]
    }

    fun calcPartToStr(s: String): Long {
        return s.toList().map { pointsPart2[it]?:0 }.fold(0)  { sum, x ->
            x.plus(sum * 5)
        }
    }
}


