package se.aoc.twentytwo


fun main() {
    val d = Day6()
    d.run()
}

class Day6 {
    lateinit var input: List<Char>
    fun run() {
        val input = Day6::class.java.getResource("/twentytwo/day6.txt")!!.readText()
        val list = createList(input)
        println("Part 1")
        println(part1())

        println("Part 2")
        println(part2())
    }

    fun createList(str: String) {
        input = str.toList()
    }

    fun part1(): Number {
        return findDistinctChars(4)
    }

    private fun findDistinctChars(no: Int): Int {
        input.forEachIndexed { i, _ ->
            if(i > (no-1)){
                val subList = input.subList(i-no, i)
                if( subList.groupingBy { it }.eachCount().maxOf { it.value } == 1) return i
            }
        }
        return 0
    }

    fun part2(): Number {
       return findDistinctChars(14)
    }
}

