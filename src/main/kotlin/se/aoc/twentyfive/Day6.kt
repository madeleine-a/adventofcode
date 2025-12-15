package se.aoc.twentyfive


fun main() {
    val d = Day6()
    d.run()
}


class Day6 {
    fun run() {
        val input = Day6::class.java.getResource("/twentyfive/6.txt").readText()
        val list = createList(input)
        println("Part 1")
        println(part1(list))

        println("Part 2")
        println(part2(list))
    }

    fun createList(str: String): List<Triple<Int, Int, String>> {
        val points = parseToPoints(str)
        return points


    }

    fun parseToPoints(str: String): List<Triple<Int, Int, String>> {
        val list = str.lines()
        val symbols = list.last().toList()
        val colWidths = mutableListOf<Int>()
        var count = 1
        symbols.drop(1).forEach {
            if (it == ' ') count++
            else {
                colWidths.add(count)
                count = 1
            }
        }
        colWidths.add(colWidths.max())

        val newResult = str.lines().map { line ->
            val list = mutableListOf<String>()
            var workingLine = line
            colWidths.forEach { colWidth ->
                val chunk = if (colWidth > 1) workingLine.take(colWidth) else workingLine
                list.add(chunk)
                if (colWidth > 1) workingLine = workingLine.drop(colWidth)
            }
            list
        }
        val res = newResult.mapIndexed { y, list ->
            list.mapIndexed { x, v ->
                Triple(x, y, v)
            }
        }
        return res.flatten()
    }

    fun part1(values: List<Triple<Int, Int, String>>): Long {
        return values.groupBy { it.first }.map { (_, list) ->
            val symbol = list.last().third.trim()
            val numbers = list.dropLast(1).map { it.third.trim().toLong() }
            val res = if (symbol == "+") numbers.sum()
            else numbers.reduce { acc, i -> acc * i }
            res
        }.sum()
    }

    fun part2(values: List<Triple<Int, Int, String>>): Long {
        val workingList = values.groupBy { it.first }.map { (_, list) -> list.map { it.third }}.map { problem->
            val symbol = problem.last().trim()
            val numbers = problem.dropLast(1).map {
                val chars = it.toList()
                chars.mapIndexed { x, char -> x to char }
            }.flatten().groupBy { it.first }.mapNotNull { (_, list) ->
                val num = list.joinToString("") { it.second.toString().trim() }
                if(num != "") num.toLong() else null
            }
            symbol to numbers
        }
        return workingList.sumOf { (symbol, numbers) ->
            val res = if (symbol.trim() == "+") numbers.sum()
            else numbers.reduce { acc, i -> acc * i }
            res
        }.toLong()
    }

}
