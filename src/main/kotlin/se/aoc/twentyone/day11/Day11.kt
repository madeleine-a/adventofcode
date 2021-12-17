package se.aoc.twentyone.day11

class Day11 {
    lateinit var octupuses: MutableList<Octupus>
    fun run() {
        val input = Day11::class.java.getResource("/twentyone/day11.txt").readText()
        val list = input.split("\r\n")

        println("Part 1")
        println(part1(list))

        println("Part 2")
        println(part2(list))
    }

    fun init(list: List<String>){
        octupuses = list.mapIndexed { y, str ->
            str.toList().mapIndexed { x, c -> Octupus(x, y, c.digitToInt()) }
        }.flatten().toMutableList()
        octupuses.forEach { o
            ->
            o.getAdjacentPoints(octupuses)
        }
    }


    fun part1(list: List<String>): Number {
        init(list)
        for (i in 0 until 100) {
            octupuses.forEach { o ->
                o.increaseLevel(i)
                o.flash(i)
            }
        }
        return octupuses.sumOf { o -> o.flashes.values.count() }
    }



    fun part2(list: List<String>): Number {
        init(list)
        var i = 0
        var cont = true
        while(cont){
            i++
            octupuses.forEach { o ->
                o.increaseLevel(i)
                o.flash(i)
            }
            val flashedOctopuses = octupuses.filter { o -> o.flashes[i] == true }
            cont = flashedOctopuses.size != octupuses.size
        }
        return i
    }

    fun printOctopuses(octupuses: List<Octupus>) {
        val print = octupuses.groupBy { it.y }
        print.forEach { p ->
            println(p.value.map { l -> l.value }.joinToString(""))
        }
        println("")
        println("")

    }


}


