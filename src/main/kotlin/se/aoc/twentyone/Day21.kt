package se.aoc.twentyone

class Day21 {
    val list = mutableListOf<Int>()
    fun init() {
        list.addAll((1..100).toList())
        list.addAll((1..100).toList())
        list.addAll((1..100).toList())
    }

    fun run() {
        /**
         * Player 1 starting position: 3
         * Player 2 starting position: 4
         */
        val p1 = Player(1, 3)
        val p2 = Player(2, 4)
        println("Part 1")
        println(part1(p1, p2))

        println("Part 2")
        println(part2(p1, p2))
    }


    fun part1(p1: Player, p2: Player): Number {
        init()
        var end = 0
        run loop@{
            while (true) {
                for (l in list.windowed(3, 3)) {
                    end += 3
                    if (l.first() % 2 == 0) {
                        p2.roll(l)
                        if (p2.score >= 1000) {
                            return@loop
                        }
                    } else {
                        p1.roll(l)
                        if (p1.score >= 1000) {
                            return@loop
                        }
                    }
                }
            }
        }
        return end * listOf(p1, p2).minOf { it.score }
    }

    fun part2(p1: Player, p2: Player): Number {
        init()
        var end = 0
        var counter = 0
        while (p1.score < 21 && p2.score < 21) {
            val l = listOf(1, 2, 3)
            end += 3
            if (l.first() % 2 == 0) {
                p2.rollQuantum(l)
            } else {
                p1.rollQuantum(l)
            }
            counter++
        }

        return 0

    }

    private val dieOutcomes = 3..9

    private fun outcomesCount(outcome: Int): Int {
        return when(outcome){
            3 -> 1
            4 -> 3
            5 -> 6
            6 -> 7
            7 -> 6
            8 -> 3
            9 -> 1
            else -> 0
        }
    }
}

class Player(private val id: Int, private val startPos: Int) {
    var currPos = startPos
    var score = 0
    fun roll(ints: List<Int>) {
        currPos += ints.sum() % 10
        if (currPos > 10) currPos %= 10
        score += currPos
    }

    private var universes = mutableListOf(Pair(startPos, score))

    fun rollQuantum(ints: List<Int>) {
        println(universes.size)
        var toSplit = mutableListOf<Pair<Int, Int>>()
        toSplit.addAll(universes)
        universes = mutableListOf()
        for (i in ints) {
            for (p in toSplit) {
                var v = p.first + i % 10
                if (v > 10) v %= 10
                if (p.second + v >= 21) {
                    this.score = p.second + v
                }
                universes.add(Pair(v, p.second + v))
            }
        }
    }

    override fun toString(): String {
        return "Player(id=$id, startPos=$startPos, currPos=$currPos, score=$score)"
    }
}

