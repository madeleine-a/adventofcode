package se.aoc.twentyone

class Day14 {
    var str = "CHBBKPHCPHPOKNSNCOVB"
        set(value) {
            field = value
        }
    private lateinit var template: Map<String, String>
    private lateinit var polymer: List<String>
    private lateinit var countOfChars: MutableMap<String, Long>
    private lateinit var countOfPairs: MutableMap<String, Long>

    fun run() {
        val input = Day14::class.java.getResource("/twentyone/day14.txt").readText()
        val list = input.split("\r\n")

        println("Part 1")
        println(part1(list))

        println("Part 2")
        println(part2(list))
    }

    private fun init(input: List<String>) {
        template = input.map { it.split(" -> ") }.map { l -> l.first() to l.last() }.toMap()
        polymer = str.toList().map { c -> c.toString() }
        countOfChars = polymer.groupingBy { it }.eachCount().map { it.key to it.value.toLong() }
            .toMap() as MutableMap<String, Long>
        countOfPairs = polymer.windowed(2).map { it.joinToString("") }.groupingBy { it }.eachCount()
            .map { it.key to it.value.toLong() }.toMap() as MutableMap
    }

    fun part1(input: List<String>): Number {
        init(input)
        return runPolymerV2(10)
    }


    fun part2(input: List<String>): Number {
        init(input)
        return runPolymerV2(40)
    }

    private fun runPolymerV2(no: Int): Number {
        for (j in 0 until no) {
            countOfPairs.filterNot { it.value < 1 }.forEach { pair ->
                template[pair.key]?.also { char ->
                    countOfPairs.merge(pair.key[0] + char, pair.value) { a, b -> a + b }
                    countOfPairs.merge(char + pair.key[1], pair.value) { a, b -> a + b }
                    // remove the one we just split up
                    countOfPairs.merge(pair.key, pair.value) { a, b -> a - b }
                    countOfChars.merge(char, pair.value) { a, b -> a + b }
                }
            }
        }
        return countOfChars.values.maxOf { it } - countOfChars.values.minOf { it }
    }
}