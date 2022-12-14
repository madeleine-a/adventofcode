package se.aoc.twentytwo

import se.aoc.Stack
import se.aoc.push


fun main() {
    val d = Day13()
    d.run()
}

class Day13 {
    lateinit var list: List<Pair<ListValue, ListValue>>
    fun run() {
        val input = Day1::class.java.getResource("/twentytwo/day13.txt").readText()
        createList(input)
        println("Day 13")
        println("Part 1")
        println(part1())

        println("Part 13")
        println(part2())
    }

    fun createList(str: String) {
        val l = str.split(System.lineSeparator()).windowed(3, 3, true).map { it[0] to it[1] }
        list = l.map { pair ->
            charsToListValue(
                pair.first.replace("[", "[,").replace("]", ",]").split(",")
                    .filter { it.isNotEmpty() }) to charsToListValue(
                pair.second.replace("[", "[,").replace("]", ",]").split(",").filter { it.isNotEmpty() })
        }
    }


    fun charsToListValue(chars: List<String>): ListValue {
        val stackA: Stack<Value> = mutableListOf()
        var index = 0
        chars.forEachIndexed loop@{ i, c ->
            if (i < index) {
                return@loop
            }
            if (c == "[") {
                val d = mapData(chars.slice(i + 1 until chars.size), i + 1)
                stackA.add(d.first)
                index = d.second + 1
            } else if (c == "]") {
                return ListValue(stackA)
            } else stackA.push(IntValue(c.toInt()))

        }
        return ListValue(stackA)
    }

    fun mapData(chars: List<String>, no: Int): Pair<ListValue, Int> {
        val stackA: Stack<Value> = mutableListOf()
        var index = 0
        chars.forEachIndexed loop@{ i, c ->
            if (i < index) {
                return@loop
            }
            if (c == "[") {
                val d = mapData(chars.slice(i + 1 until chars.size), i + 1)
                stackA.add(d.first)
                index = d.second + 1
            } else if (c == "]") {
                return ListValue(stackA) to no + i
            } else stackA.push(IntValue(c.toInt()))
        }
        return ListValue(stackA) to 0
    }


    fun part1(): Number {
        val res = list.mapIndexed { index, pair ->
            pair.first.compare(pair.second) to index + 1
        }
        return res.filter { it.first == true }.map { it.second }.reduce { acc, i -> acc + i }
    }

    fun compare(a: ListValue, b: ListValue): Boolean {
        return a.compare(b) == true
    }

    fun part2(): Number {
        val packages = list.map { listOf(it.first, it.second) }.flatten().toMutableList()
        val l = "[[2]]".split("").filter {  it.isNotEmpty()}
        packages.add(charsToListValue("[[2]]".split("").filter {  it.isNotEmpty()}))
        packages.add(charsToListValue("[[6]]".split("").filter {  it.isNotEmpty()}))
       val sorted =
           packages.sorted().mapIndexedNotNull { index, listValue ->
            if(listValue.getIntList() == listOf(2) || listValue.getIntList() == listOf(6)) {
                index+1
            }else {
                null
            }
        }.reduce { acc, i -> acc*i  }
        return sorted
    }
}


abstract class Value(open val value: Any){
    abstract fun isList(): Boolean
    abstract fun asList(): ListValue
    abstract fun size(): Int
    abstract fun toInt(): Int


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Value) return false

        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

}

data class IntValue(override val value: Int) : Value(value) {
    override fun isList() = false
    override fun asList() = ListValue(listOf(this))
    override fun size() = 1
    override fun toInt() = value
}

data class ListValue(override val value: List<Value>) : Value(value), Comparable<ListValue>  {
    override fun isList() = true
    override fun asList() = this
    override fun size() = value.size
    override fun toInt() = -1
    fun get(i: Int): Value {
        return value[i]
    }

    fun isEmpty(): Boolean {
        return this.value.isEmpty()
    }

    fun getIntList(): List<Int> {
        return value.map {
            if (!it.isList()) {
                val v = it as IntValue
                listOf(v.toInt())
            } else {
                val l = it.asList()
                l.getIntList()
            }
        }.flatten()
    }

    override fun compareTo(other: ListValue): Int {
        return if(this.equals(other)) 0
        else if(this.compare(other) == true) -1
        else 1
    }

    fun compare(other: Value): Boolean? {
        val a = this.asList()
        val b = other.asList()
        val aSize = a.size()
        val bSize = b.size()

        a.value.forEachIndexed { index, value ->
            if (index >= b.value.size) {
                return false
            }
            if (value.isList() || b.value[index].isList()) {
                val result = value.asList().compare(b.value[index])
                if (result != null) return result
            } else if (!value.isList() && !b.get(index).isList()) {
                val valB = b.asList().get(index) as IntValue
                if (value.toInt() < valB.toInt()) return true
                else if (value.toInt() > valB.toInt()) return false
            }
        }
        if (aSize < bSize) return true
        return null
    }
}
