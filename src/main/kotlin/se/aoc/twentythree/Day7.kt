package se.aoc.twentythree



val deck = "A, K, Q, J, T, 9, 8, 7, 6, 5, 4, 3, 2".split(", ").reversed().mapIndexed { index, s ->
    s to index
}.toMap()

val deck2 = "A, K, Q, T, 9, 8, 7, 6, 5, 4, 3, 2, J".split(", ").reversed().mapIndexed { index, s ->
    s to index
}.toMap()

fun main() {
    val d = Day7()
    d.run()
}

class Day7 {
    fun run() {
        val input = Day7::class.java.getResource("/twentythree/day7.txt").readText()
        val list = createList(input)
        println("Part 1")
        println(part1(list)) // 248179786

        println("Part 2")
        println(part2(list)) // 54518
    }
    private val part1Comparator = Comparator<Hand> { hand1, hand2 ->
        val res = hand1.getType().compareTo(hand2.getType())
        if(res!=0) res else {
            val no =
                hand1.getValue(deck).zip(hand2.getValue(deck)).first { it.first != it.second }
            if(no.first>no.second) 1 else -1
        }
    }

    private val part2Comparator = Comparator<Hand> { hand1, hand2 ->
        val res = hand1.getTypeWithJokers().compareTo(hand2.getTypeWithJokers())
        if(res!=0) res else {
            val no =
                hand1.getValue(deck2).zip(hand2.getValue(deck2)).first { it.first != it.second }
            if(no.first>no.second) 1 else -1
        }
    }

    fun createList(str: String): List<Hand> {
        return str.lines().map { s ->
            val x = s.split(" ")
            Hand(x.first(), x.last().toInt())
        }
    }

    fun part1(input: List<Hand>): Number {
        val res = input.sortedWith(part1Comparator).mapIndexed { index, hand ->
            hand.apply { this.rank = index+1 }
        }
        return res.sumOf { it.getWinning() }
    }

    fun part2(input: List<Hand>): Number {
        val res = input.sortedWith(part2Comparator).mapIndexed { index, hand ->
            hand.apply { this.rank = index+1 }        }
        return res.sumOf { it.getWinning() }
    }
}

data class Hand(val cards: String, val bid: Int, var rank: Int? = null) {
    fun getWinning() = (rank ?: 0) * bid
    fun getValue(deck: Map<String, Int>): List<Int> {
        return cards.toList().map { deck[it.toString()]!! }
    }

    fun getTypeWithJokers(): Int {
        val noOfJ = cards.toList().filter { it=='J' }.size
        if(noOfJ==4) return 25
        if(noOfJ==5) return 25

        val list = this.cards.toList().filter { it!='J' }
            .groupingBy { it }.eachCount()
            .values.sorted().filter { it > 1 }
        if(list.isEmpty()) return (noOfJ+1)*(noOfJ+1)
        if(list.size == 2 && noOfJ == 1) return (2*2 + 3*3)
        return list.sumOf { (it+noOfJ) * (it+noOfJ) }
    }

    fun getType(): Int {
        return this.cards.toList().groupingBy { it }.eachCount()
            .values.sorted().filter { it > 1 }.sumOf { it * it }
    }
}
