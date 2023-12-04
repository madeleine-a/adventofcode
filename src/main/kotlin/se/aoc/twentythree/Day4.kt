package se.aoc.twentythree



fun main() {
    val d = Day4()
    d.run()
}

class Day4 {
    fun run() {
        val input = Day4::class.java.getResource("/twentythree/day4.txt").readText()
        val list = createList(input)
        println("Part 1")
        println(part1(list))

        println("Part 2")
        println(part2(list))
    }

    fun createList(str: String): Map<Card, Set<Int>> {
        val list = str.lines().map { s -> s.split("|") }
            .map { l ->
                val c = l.first().split(":")
                val numbers = l.last().split(" ").mapNotNull { if(it.isNumber()) it.toInt() else null }.toSet()
                val card = Card(c.first().replace("Card", "").trim().toInt(),
                    c.last().split(" ").mapNotNull { if(it.isNumber()) it.toInt() else null }.toSet())
                card to numbers
            }.toMap()
        return list
    }

    fun part1(input: Map<Card, Set<Int>>): Number {
        return input.map { (card, numbers) ->
             numbers.filter {card.numbers.contains(it) }.foldIndexed(0) { index, acc, _ ->  if(index==0)acc+1 else acc*2}

        }.sum()
    }

    fun part2(input: Map<Card, Set<Int>>): Number {
        input.forEach { (card, numbers) ->
            val matching = numbers.filter {card.numbers.contains(it) }.size
            card.wonCards = input.keys.toList().slice(card.id until card.id+matching)
        }
        return input.keys.sumOf { it.sumOfCards() }
    }
}

data class Card (val id: Int, val numbers: Set<Int>, var wonCards: List<Card>? = null){
    fun sumOfCards():Int = 1.plus(wonCards?.map { it.sumOfCards() }?.sum()?:0)
}
