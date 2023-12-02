package se.aoc.twentythree

fun main() {
    val d = Day2()
    d.run()
}

class Day2 {
    private val bag = mapOf("red" to 12, "green" to 13, "blue" to 14)

    fun run() {
        val input = Day2::class.java.getResource("/twentythree/day2.txt").readText()
        val list = createGames(input)
        println("Part 1")
        println(part1(list))

        println("Part 2")
        println(part2(list))
    }

    fun createGames(str: String): List<Game> {
        val list = str.split("\r\n").map{it.split(":", ";")}
        return list.map { l ->
            val id = l.first().replace("Game ", "").toInt()
            val sets = l.subList(1, l.size).map { s ->
                s.trim().split(",").map { it.trim().split(" ")}.associate { it.last() to it.first().toInt() }
            }
            Game(id, sets)
        }
    }

    private fun isPossible(g: Game): Boolean = g.sets.all{ s -> s.all { (key, value) -> bag[key]!! >= value }}
    fun part1(games: List<Game>): Number {
        return games.filter { g -> (isPossible(g)) }.sumOf { it.id }
    }

    fun part2(games: List<Game>): Number {
        return games.sumOf { it.power() }
    }
}

data class Game(val id: Int, val sets: List<Map<String, Int>>){
    fun power(): Int {
        val result = sets.fold(mutableMapOf("red" to 0, "green" to 0, "blue" to 0)){ acc, set ->
            set.forEach{(key, value) -> if(acc[key]!! < value) acc[key] = value }
            acc
        }
        return result.values.reduce { acc, i -> acc*i }
    }
}

