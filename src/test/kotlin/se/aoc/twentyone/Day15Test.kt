package se.aoc.twentyone

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import se.aoc.twentyone.day15.Day15

internal class Day15Test {
    val day = Day15()
    val input = Day15::class.java.getResource("/twentyone/day15test.txt").readText()


    @Test
    fun run() {
        day.run()
    }

    @Test
    fun part1() {
        assertEquals(40, day.part1(input))
    }

    @Test
    fun part2() {
    }

    fun dijkstra(source: Int, edges: Array<IntArray>, nodes: Int) {
        // Initialize single source
        val d = IntArray(nodes) { Integer.MAX_VALUE }
        val pi = IntArray(nodes) { -1 }
        d[source] = 0

        val S: MutableList<Int> = ArrayList()
        val Q: MutableList<Int> = (0 until nodes).toMutableList()

        // Iterations
        while (Q.isNotEmpty()) {
            val u: Int = extractMin(Q, d)
            S.add(u)

            edges[u].forEachIndexed { v, vd ->
                if (vd != -1 && d[v] > d[u] + vd) {
                    d[v] = d[u] + vd
                    pi[v] = u
                }
            }
        }

        println("d: ${d.contentToString()}")
        println("pi: ${pi.contentToString()}")
    }

    fun extractMin(Q: MutableList<Int>, d: IntArray): Int {
        var minNode = Q[0]
        var minDistance: Int = d[0]

        Q.forEach {
            if (d[it] < minDistance) {
                minNode = it
                minDistance = d[it]
            }
        }

        Q.remove(minNode)
        return minNode
    }
}