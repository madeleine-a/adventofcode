package se.aoc.twentyone

class Day4 {
    var numbers = listOf<Int>()
    var boards = mutableListOf<Board>()

    fun run() {
        val input = Day4::class.java.getResource("/twentyone/day4.txt").readText()
        println("Part 1")
        println(part1(input))

        println("Part 2")
        println(part2(input))
    }

    private fun init(input: String) {
        val d = input.split("\r\n\r\n")
        numbers = d[0].split(",").map { it.toInt() }
        for (i in 1 until d.size) {
            val board = Board(i)
            d[i].split("\r\n").forEach { s ->
                val r = s.trim().split(" ").filter { it != "" }.associate {
                    it.toInt() to false
                }.toMutableMap()
                board.addRow(r)
            }
            boards.add(board)
        }
    }

    fun part1(input: String): Number {
        init(input)
        // Start playing
        for (i in numbers.indices) {
            for (b in boards) {
                b.markNumber(numbers[i])
                if (b.hasBingo()) {
                    return b.getUnmarkedSum() * numbers[i]
                }
            }
        }
        return 0
    }

    fun part2(input: String): Number {
        init(input)
        var list = boards
        for (i in numbers.indices) {
            for (b in list) {
                b.markNumber(numbers[i])
                if (b.hasBingo() && list.none { !it.hasBingo() }) {
                    return b.getUnmarkedSum() * numbers[i]
                }
            }
            list = list.filter { b -> !b.hasBingo() }.toMutableList()
        }
        return 0
    }
}

data class Board(val id: Int) {
    var rows = mutableListOf<MutableMap<Int, Boolean>>()
    var columns = mutableListOf<MutableMap<Int, Boolean>>()

    fun addRow(row: MutableMap<Int, Boolean>) {
        if (columns.isEmpty()) {
            for (i in 1..5) {
                columns.add(mutableMapOf<Int, Boolean>())
            }
        }
        this.rows.add(row)
        var i = 0
        for (v in row) {
            columns[i][v.key] = v.value
            i++
        }
    }

    fun markNumber(no: Int) {
        rows.forEach { r ->
            if (r[no] != null) {
                r[no] = true
            }
        }
        columns.forEach { r ->
            if (r[no] != null) {
                r[no] = true
            }
        }
    }

    fun getUnmarkedSum(): Int {
        return this.rows.flatMap { it.entries }.associate { it.key to it.value }.filter { !it.value }.keys.sum()
    }

    fun hasBingo(): Boolean {
        return (rows.any { r -> r.values.count { it } == 5 } || columns.any { r -> r.values.count { it } == 5 })
    }


    override fun toString(): String {
        return "Board(id=$id, rows=$rows)"
    }


}


