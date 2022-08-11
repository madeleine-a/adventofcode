package se.aoc.seventeen

fun main() {
    val d = Day10()
    d.run()
}

class Day10 {
    fun run() {
        val input = "14,58,0,116,179,16,1,104,2,254,167,86,255,55,122,244"
        val numbers = (0..255).toList()

        val list = input.split(",").map { it.toInt() }
        println("Part 1")
        println(part1(list, numbers))

        println("Part 2")
        println(part2(numbers, input))
    }

    fun part1(input: List<Int>, numbers: List<Int>): Number {
        val result = runRounds(1, input, numbers)
        return result[0] * result[1]
    }

    fun runRounds(noOfRounds: Int, input: List<Int>, numbers: List<Int>): List<Int> {
        var pos = 0
        var skipSize = 0
        var newList = numbers
        for (j in 0 until noOfRounds) {
            for (i in input) {
                // where to start next run
                val start = pos.modulo(newList.size)
                // println("start, $start, pos, $pos, skipSize, $skipSize, i, $i, j, $j")
                if (i > 1) {
                    val seq = seqAtPos(newList, start)
                    newList = seq.take(i).toList().reversed().plus(seq.drop(i).take(newList.size - i%newList.size).toList())
                    //println(newList)
                    newList = seqAtPos(newList, newList.size - start).toList()
                    //println(newList)
                }
                pos = start + skipSize + i
                skipSize++
            }
        }
        return newList
    }

    fun seqAtPos(list: List<Int>, start: Int): Sequence<Int> {
        val seq = sequence {
            yieldAll(list)
            yieldAll(list)
        }.drop(start).take(list.size)
        return seq
    }

    fun part2(input: List<Int>, str: String): String {
        val seqList = "17, 31, 73, 47, 23".split(",").map { it.trim().toInt() }
        val asciiList = str.map { it.code }
        val seq = sequence {
            yieldAll(asciiList)
            yieldAll(seqList)
        }
        val result = runRounds(64, seq.toList(), input)
        println(result)
        return result.chunked(16).joinToString("") { it.xor().dense() }
    }

    fun Int.dense(): String{
        val ret = this.toString(16)
        return if(ret.length == 1)
            "0$ret"
        else ret
    }
    fun List<Int>.xor(): Int{
        return this.reduce { acc, i -> acc xor i }
    }


}


infix fun Int.modulo(modulus: Int): Int {
    if (modulus <= 0) throw ArithmeticException("modulus $modulus must be > 0")
    val remainder = this % modulus
    return if (remainder >= 0) remainder else remainder + modulus
}