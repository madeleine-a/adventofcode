package se.aoc.eighteen

class Day1 {
    fun run() {
        val input = Day1::class.java.getResource("/eighteen/day1.txt").readText()
        val list = input.split("\r\n").map { it.toInt() }
        println("Part 1")
        println(part1(list))

        println("Part 2")
        println(part2(list))
    }

    fun part1(input: List<Int>): Number {
        return input.sum()
    }

    fun part2(input: List<Int>): Number {
        var initialValue = 0
        var found = false;
        var sums = mutableListOf<Int>()

        while (!found) {
            val calSums = calculateSum(initialValue, input)
            val count = (calSums+sums).groupingBy { it }.eachCount().filter { it.value > 1 }
            if (count.isNotEmpty()) {
                return count.keys.first()
            }
            sums.addAll(calSums)
            initialValue = sums.last()
            sums.removeLast()
        }
        return 0
    }

    private fun calculateSum(initialValue: Int, input: List<Int>): List<Int> {
        return input.runningFold(initialValue) { sum, item -> sum + item }
    }
}