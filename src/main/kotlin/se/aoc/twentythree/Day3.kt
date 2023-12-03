package se.aoc.twentythree

fun main() {
    val d = Day3()
    d.run()
}

class Day3 {

    fun run() {
        val input = Day3::class.java.getResource("/twentythree/day3.txt").readText()
        val list = createList(input)
        println("Part 1")
        println(part1(list))

        println("Part 2")
        println(part2(list))
    }

    fun createList(str: String): List<Point> {
        var noList = mutableMapOf<Point, String>()
        val res = str.lines().mapIndexed { x, s ->
            s.mapIndexed { y, c ->
                val point =  Point(x, y, c.toString())
                if(c.isDigit()){
                    noList[point] =  c.toString()
                }else{
                    val no = noList.values.joinToString (separator = "")
                    noList.keys.forEach { p -> p.value = no }
                    noList = mutableMapOf()
                }
                point
            }
        }
        return res.flatten()
    }

    fun part1(input: List<Point>): Number {
        var list = input.filter { it.value != "." }
        var last: Point? = null
        var left = list.filter {
            it.value.isNumber() && it.getAdjacentPoints(list).any { p -> !p.value.isNumber() }
        }.map { p ->
            if(last == null || last?.value != p.value || last?.x != p.x || last?.y != p.y-1){
                last = p
                p.value.toInt()
            }else {
                last = p
                0
            }
        }
        return left.sum()
    }

    fun part2(input: List<Point>): Number {
        var list = input.filter { it.value == "*" }
        var last: Point? = null

        val res = list.map { p ->
            val numbers = p.getAdjacentPoints(input).filter {
                it.value.isNumber()
            }.mapNotNull { point ->
                if (last == null ||  last?.x != point.x || last?.y != point.y - 1) {
                    last = point
                    point.value.toInt()
                } else {
                    last = point
                    null
                }
            }
            if(numbers.size>1)
                numbers.filter { it != 0 }.reduce { acc, i ->  acc*i}
            else 0
        }.sum()
        return res
    }
}

fun String.isNumber() = this.toIntOrNull() != null

