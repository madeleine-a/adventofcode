package se.aoc.twentyone

import se.aoc.Point

class Day2 {
    fun run() {
        val input = Day2::class.java.getResource("/twentyone/day2.txt").readText()
        val list = input.split("\r\n")


        println("Part 1")
        println(part1(list))

        println("Part 2")
        println(part2(list))
    }

    fun part1(input: List<String>): Number {
        val list = getCommands(input)
        val point = Point(0,0)
        list.forEach { c -> point.offset(c.getPoint().x, c.getPoint().y) }
        return point.x * point.y
    }

    fun part2(input: List<String>): Number {
        val list = getCommands(input)
        val point = Point(0,0)
        list.forEach { c -> point.aim(c.getPoint().x, c.getPoint().y) }
        return point.x * point.depth
    }

    private fun getCommands(input: List<String>): List<Command> {
        return input.map { it.split(" ")}.map { list -> list.zipWithNext().single() }
            .map { p -> Command(CommandEnum.valueOf(p.first), p.second.toInt()) }
    }
}

enum class CommandEnum {
    forward, down, up
}

class Command(val enum: CommandEnum, val value: Int){
    fun getPoint(): Point {
        return when(enum){
            CommandEnum.forward -> Point(value, 0)
            CommandEnum.down -> Point(0, value)
            CommandEnum.up -> Point(0, 0-value)
        }
    }
}
