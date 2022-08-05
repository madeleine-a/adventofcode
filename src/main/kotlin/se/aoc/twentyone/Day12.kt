package se.aoc.twentyone

class Day12 {
    private lateinit var connections: MutableList<Node>
    fun run() {
        val input = Day12::class.java.getResource("/twentyone/day12.txt").readText()
        val list = input.split("\r\n")
        println("Part 1")
        println(part1(list))

        println("Part 2")
        println(part2(list))
    }

    fun part1(input: List<String>): Number {
        connections = input.map {
            it.split("-").zipWithNext()
                .map { p -> Node(p.first, p.second) }
        }.flatten().toMutableList()
        println(connections)
        var queue = mutableListOf<Node>()
        val start = connections.filter { it.a == "start" || it.b == "start" }
        val paths = listOf<List<Node>>()
        var counter = 0
        start.forEach { s ->
            s.visited = true
            queue.add(0, s)
            while (queue.isNotEmpty()) {
                val q = queue.first()
                queue.removeFirst()
                val list = connections.filter { !it.isStart() && (it.a == q.b || q.b == it.b) && !it.visited}
                list.forEach { l ->
                    if (l.b == "end") {
                        counter += 1
                    } else {
                        if (l.b[0].isLowerCase() && l.a[0].isLowerCase()) {
                            l.visited = true
                        }
                        queue.add(0, l)
                    }
                }
                println(queue)

            }
        }
        println(counter)
        return 0
    }


    fun part2(input: List<String>): Number {
        return 0
    }
}

class Node(val a: String, val b: String) {
    var visited = false
    fun isEnd() = this.a == "end" || this.b == "end"
    fun isStart() = this.a == "start" || this.b == "start"
    override fun toString(): String {
        return "Node(a='$a', b='$b', visited=$visited)"
    }

}

