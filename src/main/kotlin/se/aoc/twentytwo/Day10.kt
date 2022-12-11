package se.aoc.twentytwo


fun main() {
    val d = Day10()
    d.run()
}

class Day10 {
    lateinit var data: List<Instr>

    fun run() {
        val input = Day10::class.java.getResource("/twentytwo/day10.txt").readText()
        createList(input)
        println("Day 10")
        println("Part 1")
        println(part1())
        createList(input)
        println("Part 10")
        println(part2())
    }

    fun createList(str: String) {
        val l = str.split(System.lineSeparator()).map { it.split(" ") }
        data = l.map { r ->
            if (r.size > 1) Instr(Com.valueOf(r.first().uppercase()), r.last().toInt())
            else Instr(Com.valueOf(r.first().uppercase()))
        }
    }

    fun part1(): Number {
        return runProgram()
    }


    private fun runProgram(): Int {
        var i = 0
        var x = 1
        var signalStrength = 0
        val screen = Screen()

        screen.initRows()

        data.forEach { instr ->
            for (j in 1..instr.command.time) {
                screen.drawPixel(i)
                i += 1
                if (i == 20 || (i - 20) % 40 == 0) {
                    signalStrength += i * x
                }

            }

            when (instr.command) {
                Com.NOOP -> {}
                Com.ADDX -> {
                    x += instr.value!!
                    screen.moveSprite(i, instr.value)
                }
            }
        }
        screen.drawScreen()
        return signalStrength
    }

    fun part2(): Number {
        return 0
    }
}

data class Screen(val pixels: MutableMap<Int, String> = mutableMapOf()) {
    private var sprite: List<Int> = listOf(0, 1, 2)
    private var row = 0
    fun initRows() {
        pixels.putAll((0 until 240).associateWith { " " } as MutableMap<Int, String>)
    }


    fun drawPixel(i: Int) {
        val p = i % 40
        if (sprite.contains(p)) {
            this.pixels[i] = "#"
        }
    }

    fun moveSprite(pos: Int, i: Int) {
        sprite = listOf(sprite[0] + i, sprite[1] + i, sprite[2] + i)
    }

    fun drawScreen() {
        pixels.values.windowed(40, 40).forEach { list ->
            println(list.reduce { acc, c -> acc + c })
        }
    }

}

data class Instr(val command: Com, val value: Int? = null)

enum class Com(val time: Int) {
    NOOP(1), ADDX(2);
}