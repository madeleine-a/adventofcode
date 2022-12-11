package se.aoc.twentytwo

fun main() {
    val d = Day11()
    d.run()
}

class Day11 {
    lateinit var data: Map<Int, Monkey>

    fun run() {
        val input = Day11::class.java.getResource("/twentytwo/day11.txt").readText()
        createList(input)
        println("Day 11")
        println("Part 1")
        println(part1())
        createList(input)
        println("Part 11")
        println(part2())
    }

    fun createList(str: String) {
        val l = str.split(System.lineSeparator()).windowed(7, 7, true)
        data = l.map { m ->
            val id = m[0].split(" ", ":")[1].toInt()
            val items = m[1].split(":")[1].split(",").map { it.trim().toLong() }
            val o = m[2].split(":")[1].trim().split(" ")
            val op = Operation(o[2].trim(), o[3].trim(), o[4].trim())
            val no = m[3].split(":")[1].split(" ").last().toLong()
            val atTrue = m[4].split(" ").last().toInt()
            val atFalse = m[5].split(" ").last().toInt()
            val test = Test(no, atTrue, atFalse)
            id to Monkey(id, items, op, test)
        }.toMap()
    }


    fun part1(): Number {
        var inspectTimes = data.map { (_, v) -> v.id to 0L }.toMap() as MutableMap<Int, Long>
        for (i in 0 until 20) {
            inspectTimes = doRound(inspectTimes, 3)
        }
        val l = inspectTimes.values.sortedDescending()
        return l[0] * l[1]
    }

    fun part2(): Number {

        var inspectTimes = data.map { (_, v) -> v.id to 0L }.toMap() as MutableMap<Int, Long>
        val relief = data.map { (_, m) -> m.test.no }.reduce { acc, l -> acc * l }
        for (i in 1..10000) {
            inspectTimes = doRound(inspectTimes, relief)
        }

        val l = inspectTimes.values.sortedDescending()
        return l[0] * l[1]
    }

    private fun inspectItem(level: Long, operation: Operation): Long {
        val a = if (operation.partA == "old") level else operation.partA.toLong()
        val b = if (operation.partB == "old") level else operation.partB.toLong()
        return when (operation.op) {
            "+" -> a + b
            "*" -> a * b
            "-" -> a - b
            "/" -> a / b
            else -> 0L
        }
    }

    private fun doRound(inspectTimes: MutableMap<Int, Long>, relief: Long): MutableMap<Int, Long> {
        data.forEach { (_, monkey) ->
            monkey.items.forEach {
                val res = inspectItem(it, monkey.operation)
                val level = if (relief == 3L) res / relief else {
                    res % relief
                }
                inspectTimes[monkey.id] = inspectTimes[monkey.id]!! + 1L
                if (level % monkey.test.no == 0L) {
                    data[monkey.test.atTrue]?.addItem(level)
                } else {
                    data[monkey.test.atFalse]?.addItem(level)
                }
                monkey.removeItem(it)
            }
        }
        return inspectTimes
    }


}

data class Monkey(val id: Int, var items: List<Long>, val operation: Operation, val test: Test) {
    fun addItem(i: Long) {
        this.items = items + i
    }

    fun removeItem(i: Long) {
        items = items.filter {  it != i } + items.filter { it == i }.drop(1)
    }
}

data class Test(val no: Long, val atTrue: Int, val atFalse: Int)
data class Operation(val partA: String, val op: String, val partB: String)
