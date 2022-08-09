package se.aoc.seventeen

fun main() {
    val d = Day8()
    d.run()
}

class Day8 {
    lateinit var map: Map<String, Register>
    fun run() {
        val input = Day8::class.java.getResource("/seventeen/day8.txt").readText()
        val list = input.split("\r\n").map { it.split(" ") }
            .map { l ->
                Instruction(l[0], l[1], l[2].toInt(), Condition(l[4], l[5], l[6].toInt()))
            }
        initRegister(list)
        println("Part 1")
        println(part1(list))

        println("Part 2")
        println(part2(list))
    }

    fun initRegister(input: List<Instruction>){
        map = input.map { Register(it.name) }.associateBy { it.name }
    }

    fun part1(input: List<Instruction>): Number {
        processInstructions(input)
        return map.values.maxByOrNull { it.value }!!.value
    }

    fun part2(input: List<Instruction>): Number {
        return map.values.maxByOrNull { it.highest }!!.highest
    }

    private fun processInstructions(input: List<Instruction>) {
        input.forEach { i ->
            if (runCondition(i.condition, map[i.condition.on]!!.value)) {
                map[i.name]!!.value += doInstruction(i)
                println(map[i.name])
                if( map[i.name]!!.highest < map[i.name]!!.value){
                    map[i.name]!!.highest = map[i.name]!!.value
                }
            }
        }
    }




    private fun doInstruction(instruction: Instruction): Int {
        return if (instruction.doWhat == "inc") {
            + instruction.amount
        } else {
            - instruction.amount
        }
    }

    private fun runCondition(condition: Condition, value: Int): Boolean {
        return when (condition.condition) {
            ">" -> value > condition.value
            "<" -> value < condition.value
            ">=" -> value >= condition.value
            "==" -> value == condition.value
            "<=" -> value <= condition.value
            "!=" -> value != condition.value
            else -> false
        }
    }

}
data class Register(val name: String, var value: Int = 0, var highest: Int = 0)

data class Instruction(
    val name: String,
    val doWhat: String,
    var amount: Int,
    val condition: Condition
)

data class Condition(val on: String, val condition: String, val value: Int)