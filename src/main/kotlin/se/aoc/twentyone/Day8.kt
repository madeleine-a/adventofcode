package se.aoc.twentyone

class Day8 {
    lateinit var list: List<Pair<String, String>>
    lateinit var patterns: List<Pair<String, Int>>
    private val digits = listOf(2, 3, 4, 7)

    fun run() {
        val input = Day8::class.java.getResource("/twentyone/day8.txt").readText()
        println("Part 1")
        println(part1(input))

        println("Part 2")
        println(part2(input))
    }

    fun init(input: String) {
        patterns = listOf(
            "1111110" to 0,
            "0110000" to 1,
            "1101101" to 2,
            "1111001" to 3,
            "0110011" to 4,
            "1011011" to 5,
            "1011111" to 6,
            "1110000" to 7,
            "1111111" to 8,
            "1111011" to 9
        )
        list = input.split("\r\n", " | ").map { it.trim() }.windowed(2, 2).map { Pair(it.first(), it.last()) }

    }

    fun part1(input: String): Number {
        init(input)
        return list.map { it.second }.map { s ->
            s.split(" ")
                .map { lightNumber(it, "dabcgef").count { n -> n == 1 } }
        }.flatten().count { n -> digits.contains(n) }
    }

    private fun lightNumber(input: String, pattern: String): MutableCollection<Int> {
        val map = mutableMapOf<Char, Int>()
        for (c in pattern.toList()) {
            map[c] = 0
        }
        for (l in input.toList()) {
            map[l] = 1
        }
        return map.values
    }

    fun part2(input: String): Number {
        init(input)
        val result = list.map { p ->
            val pattern = calculatePattern(p.first.split(" "))
            p.second.split(" ").map { st ->
                val s = lightNumber(st, pattern).joinToString(separator = "")
                s.mapToNumber()
            }.joinToString ("").toInt()
        }
        return result.sum()
    }

    fun calculatePattern(list: List<String>): String {
        val strList = mutableListOf('X', 'X', 'X', 'X', 'X', 'X', 'X')
        // First get the top roof
        val x = list.map { it to it.length }.sortedBy { it.second }

        strList[0] = x[1].first.filterNot { c -> x[0].first.contains(c) }.toList().first()
        val a = list.map { it.toList() }.flatten().filter { it != strList[0] }
            .groupingBy { it }.eachCount().toSortedMap()
        strList[1] = a.filter { it.value == 8 }.keys.first()
        strList[2] = a.filter { it.value == 9 }.keys.first()

        strList[4] = a.filter { it.value == 4 }.keys.first()
        strList[5] = a.filter { it.value == 6 }.keys.first()
        val b = a.filter { it.value == 7 }.keys
        if (x[2].first.toList().contains(b.first())) {
            strList[6] = b.first()
            strList[3] = b.last()
        } else {
            strList[6] = b.last()
            strList[3] = b.first()
        }

        return strList.joinToString(separator = "")
    }
}

fun String.mapToNumber(): Int {
    return when (this.toList().count { n -> n == '1' }) {
        2 -> 1
        3 -> 7
        4 -> 4
        7 -> 8
        else -> {
            when (this) {
                "1111110" -> 0
                "0110000" -> 1
                "1101101" -> 2
                "1111001" -> 3
                "0110011" -> 4
                "1011011" -> 5
                "1011111" -> 6
                "1110000" -> 7
                "1111111" -> 8
                "1111011" -> 9
                else -> {
                    println(this)
                    666
                }
            }
        }
    }

}


