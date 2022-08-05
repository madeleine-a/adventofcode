package se.aoc.seventeen

fun main() {
    val d = Day6()
    d.run()
}

class Day6 {
    val result = mutableListOf<List<Int>>()
    fun run() {
        val input = "14\t0\t15\t12\t11\t11\t3\t5\t1\t6\t8\t4\t9\t1\t8\t4"
        val list = input.split("\t").map { it.toInt() }

        runLoop(list)
        println("Part 1")
        println(part1())

        println("Part 2")
        println(part2())
    }

    fun part1(): Number {
        return result.size
    }

    fun part2(): Number {
        var map = result.withIndex().associateBy({ it.index }, { it.value })
        var same = map.toList().groupingBy { (_,v) -> v }.eachCount().filter { it.value > 1 }.keys.first()
        val filterValues = map.filterValues { it == same }
        return filterValues.keys.reduce { a,b -> b-a }
    }

    fun runLoop(input: List<Int>){
        var cont = true
        var map = input.withIndex().associateBy({ it.index }, { it.value })
        while(cont){
            val doBank = map.toList().sortedByDescending { (_, v) -> v }.toMap().keys.first()
            val blocks = map[doBank]!!
            val blockList = spreadBlocks(blocks, map.values.size)
            val list = moveBlocks(blockList, map.values.toList(), doBank)
            result.add(list)
            map = list.withIndex().associateBy({ it.index }, { it.value })
            cont = !isResultSame(result)
        }
    }



    private fun isResultSame(results: List<List<Int>>): Boolean{
        return results.groupingBy { it }.eachCount().any { it.value > 1 }
    }
    private fun moveBlocks(blocks: List<Int>, banks: List<Int>, fromBank: Int): List<Int>{
        val part1 = fromBank+1 until banks.size
        val part2 = 0 until fromBank
        val newList = banks.toMutableList()
        var j = 0
        newList[fromBank] = 0
        for(i  in part1){
            newList[i] = banks[i]+blocks[j]
            j++
        }
        for(i in part2){
            newList[i] = banks[i]+blocks[j]
            j++
        }
        newList[fromBank] = blocks[j]
        return newList
    }

    private fun spreadBlocks(blocks: Int, banks: Int): List<Int>{
        val no = blocks%banks
        if(no == 0){
            val part = blocks/banks
            return generateSequence{ part }.take(banks).toMutableList()
        }
        val part = blocks/no
        val left = blocks - no*part
        val list = generateSequence{ part }.take(no).toMutableList()
        list.add(left)
        if(list.size < banks){
            for( i in 0 until banks-list.size){
                list.add(0)
            }
        }
        return list
    }



}