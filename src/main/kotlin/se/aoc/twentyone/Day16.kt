package se.aoc.twentyone

class Day16 {
    val binaries = mapOf(
        "0" to "0000", "1" to "0001", "2" to "0010", "3" to "0011",
        "4" to "0100", "5" to "0101", "6" to "0110", "7" to "0111",
        "8" to "1000", "9" to "1001", "A" to "1010", "B" to "1011",
        "C" to "1100", "D" to "1101", "E" to "1110", "F" to "1111"
    )

    fun run() {
        val input = Day16::class.java.getResource("/twentyone/day1.txt").readText()

        println("Part 1")
        println(part1(input))

        println("Part 2")
        println(part2(input))
    }

    fun part1(input: String): String {
        val sum = interpret(toBinary(input))
        println(sum)
        return sum
    }

    fun toBinary(str: String): String {
        return str.toList().map { c -> binaries[c.toString()] }.joinToString("")
    }

    fun interpret(str: String): String {
        println(str)
        // first 3 is version
        val v = str.substring(0..2).toInt(2)
        // next 3 is type
        val t = str.substring(3..5).toInt(2)
        if (t == 4) {
            return doLiteral(str.substring(6))
        }  else {
            // Operator
            println(str.substring(6 until 7))
            val o = str.substring(6 until 7).toInt()
            println(o)
            if (o == 0) {
                doLengthPackages(str.substring(7))
            } else if (o == 1) {
                doNumberOfPackages(str.substring(7))
            }
        }
        return "0"

    }

    fun doNumberOfPackages(str: String){
        println(str.substring(0 until 11))
        val length = str.substring(0 until 11).toInt(2)
        var s = str.substring(11 )
        while( s.length > 11){
            val v = s.substring(0 until 11).toInt(2)
            s = s.substring(11)
            println(v)
        }
    }
    fun doLengthPackages(str: String) {
        println(str.substring(0 until 15))
        val length = str.substring(0 until 15).toInt(2)
        println(length)
        var s = str.substring(15 until (15+length))
        while( s.length > 11){
            if(s.length > 11*2) {
                println(s.substring(0 until 11))
                val v = s.substring(0 until 11).toInt(2)
                println(v)
            }else{
                val end = length%11 + 10
                println(end)
                val v = s.substring(0 until end).toInt(2)
                println(v)
            }

            s = s.substring(12)
        }

    }

    fun doLiteral(str: String): String {
        val values = str.windowed(5, 5).map { it.first() to it.substring(1) }
        var sum = ""
        values.forEach { pair ->
            sum += pair.second
            if (pair.first == '0') {
                return@forEach
            }
        }
        return sum
    }

    fun part2(input: String): Number {
        return 0
    }
}