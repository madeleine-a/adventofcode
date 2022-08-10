package se.aoc.seventeen

import se.aoc.Stack
import se.aoc.peek
import se.aoc.pop
import se.aoc.push

fun main() {
    val d = Day9()
    d.run()
}

class Day9 {
    fun run() {
        val input = Day9::class.java.getResource("/seventeen/day9.txt").readText()
        val list = input.toList()
        println("Part 1")
        println(part1(list))

        println("Part 2")
        println(part2(list))
    }

    fun part1(input: List<Char>): Number {
        val str = cleanOutGarbage(input)
        val g = Group(toChildren(str.substring(1 until str.lastIndex), 2).second, 1)
        return g.getTotalScore()
    }

    fun part2(input: List<Char>): Number {
        return countGarbage(input)
    }

    private fun hasGarbage(stack: Stack<Char>): Boolean {
        return stack.peek() == '<'
    }

    private fun countGarbage(input: List<Char>): Int {
        val stack: Stack<Char> = mutableListOf()
        var bin = 0
        input.forEach { c ->
            // if cancelled
            if (stack.peek() == '!') {
                stack.pop()
            } else if (c == '!') { // cancel next
                stack.push(c)
            } else if (!hasGarbage(stack)) {
                when (c) {
                    '{', '}', '<' -> stack.push(c)
                }
            } else {
                if (c == '>') {
                    stack.pop()
                }else{
                    bin++
                }
            }
        }
        return bin
    }

    private fun cleanOutGarbage(input: List<Char>): String {
        val stack: Stack<Char> = mutableListOf()
        input.forEach { c ->
            // if cancelled
            if (stack.peek() == '!') {
                stack.pop()
            } else if (c == '!') { // cancel next
                stack.push(c)
            } else if (!hasGarbage(stack)) {
                when (c) {
                    '{', '}', '<' -> stack.push(c)
                }
            } else {
                if (c == '>') {
                    stack.pop()
                }
            }
        }

        return stack.joinToString("")
    }

    fun toChildren(input: String, score: Int): Pair<String, List<Group>?> {
        val groups = mutableListOf<Group>()
        if (input.isEmpty()) return input to null

        var str = input

        while (str.isNotEmpty()) {

            if (str.startsWith("{}")) {
                groups.add(Group(score = score))
                str = str.removePrefix("{}")
            } else if (str.startsWith("{")) {
                val pair = toChildren(str.substring(1), score + 1)
                groups.add(Group(pair.second, score))
                str = pair.first
            } else if (str.startsWith("}")) {
                return str.substring(1) to groups
            }
        }
        return str to groups
    }


}


data class Group(var children: List<Group>? = null, val score: Int = 0) {
    fun getTotalScore(): Int {
        return this.score + (children?.sumOf { c -> c.getTotalScore() } ?: 0)
    }

    override fun toString(): String {
        return "Group(score=$score, children=$children}))"
    }

    fun writeString(): String {
        var childString = ""
        if(children?.isNotEmpty() == true){
            childString = children?.joinToString("") { c -> c.writeString() }.toString()
        }

        return """{$childString}"""
    }

}


