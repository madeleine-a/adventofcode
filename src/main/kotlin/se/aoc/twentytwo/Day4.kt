package se.aoc.twentytwo


fun main() {
    val d = Day4()
    d.run()
}

class Day4 {

    fun run() {
        val input = Day4::class.java.getResource("/twentytwo/day4.txt")!!.readText()
        val list = createList(input)
        println("Part 1")
        println(part1(list))

        println("Part 2")
        println(part2(list))
    }

    fun createList(str: String): List<List<IntRange>> {
        return str.split(System.lineSeparator()).map { a ->
            a.split(",")
                .map { b ->
                    val l = b.split("-")
                    val c = l.first().toInt()
                    val d = l.last().toInt()
                    if (c < d) c..d
                    else d..c
                }
        }
    }

    fun part1(input: List<List<IntRange>>): Number {
        return input.map {
            (it.first().first <= it.last().first && it.first().last >= it.last().last) ||
                    (it.last().first <= it.first().first && it.last().last >= it.first().last)
        }.count { it }
    }


    fun part2(input: List<List<IntRange>>): Number {
        return input.map { it.first().intersect(it.last()).isEmpty() }.count { !it }
    }
}

