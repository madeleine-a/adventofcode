package se.aoc.eighteen

class Day5 {
    fun run() {
        val input = Day5::class.java.getResource("/eighteen/day5.txt").readText()
        println("Part 1")
        println(part1(input))

        println("Part 2")
        println(part2(input))
    }

    fun part1(input: String): Number {
        val result = removeReacting(input, true)
        return result.length
    }

    fun part2(input: String): Number {
        val chars = input.lowercase().toList().distinct()
        val results = mutableMapOf<Char,Int>()
        for(c in chars){
            var i = removeReacting(input.replace(c.toString(), "" , true), true)
            results[c] = i.length
        }
       return results.values.minOrNull()!!
    }

    private fun removeReacting(input: String, doEven: Boolean): String {
        val s = if (doEven) {
            String(input.toList().windowed(2, 2).filter { !isReacting(it) }.flatten().toCharArray())
        } else {
            input.first().toString() + String(input.substring(1).toList().windowed(2, 2).filter { !isReacting(it) }
                .flatten().toCharArray()) + input.last()
        }
        return if (s == input) {
            s
        } else {
            removeReacting(s, !doEven)
        }

    }

    private fun isReacting(list: List<Char>): Boolean {
        return ((list.first().isLowerCase() && list.last().isUpperCase()) ||
                (list.first().isUpperCase() && list.last().isLowerCase())) && list.first().lowercase() == list.last()
            .lowercase()
    }


}