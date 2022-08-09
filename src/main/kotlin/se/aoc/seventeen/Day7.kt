package se.aoc.seventeen

fun main() {
    val d = Day7()
    d.run()
}

class Day7 {
    fun run() {
        val input = Day7::class.java.getResource("/seventeen/day7.txt").readText()
        val list = input.split("\n").map { it.split("->", "(", ")").map { it.trim() }.filterNot { it == "" } }
            .sortedBy { it.size }
        val programs = mutableMapOf<String, Program>()
        for (l in list) {
            val p = Program(l[0], l[1].toInt())
            if (l.size > 2) {
                val programNames: List<String> = l[2].split(",").map { it.replace(" ", "") }
                p.programs = programNames
            }
            programs[l[0]] = p
        }
        println("Part 1")
        val bottom = part1(programs)
        println(bottom)

        println("Part 2")
        println(part2(programs, bottom))
    }

    fun part1(programs: Map<String, Program>): String {
        // Get all that has one on top of them
        val havePrograms = programs.toList().filter { (_, v) -> v.programs != null }

        val nobodyUnderIt = havePrograms.filter { (k, _) ->
            havePrograms.none() { it.second.programs?.contains(k) == true && it.second.name != k }
        }
        return nobodyUnderIt.first().first
    }

    fun part2(programs: Map<String, Program>, bottom: String): Number {
        val bottomProgram = programs[bottom]
        var cont = true
        val childSums = sumWeight(programs, bottomProgram!!).groupBy { it.second }

        var correctWeight = childSums.filter { it.value.size > 1 }.keys.first()
        var wrongWeight = childSums.filter { it.value.size == 1 }.values.first().first()
        // Run down
        while(cont){
            val sums = programs[wrongWeight.first]?.let { sumWeight(programs, it) }?.groupBy { it.second }
            if(sums!!.size > 1){
                wrongWeight = sums.filter { it.value.size == 1 }.values.first().first()
                correctWeight = sums.filter { it.value.size > 1 }.keys.first()
            }else{
                cont = false
            }

        }
        val diff = wrongWeight.second - correctWeight
        return programs[wrongWeight.first]!!.size - diff
    }

    fun sumWeight(programs: Map<String, Program>, programToSum: Program): List<Pair<String, Int>> {
        val list = programs.values.filter { p -> programToSum.programs?.contains(p.name) == true  }
        return list.map { it.name to (it.size + sumWeight(programs, it).sumOf { it.second })}
    }

}

class Program(val name: String, val size: Int) {
    var programs: List<String>? = null

    override fun toString(): String {
        return "Program(name='$name', size=$size, programs=$programs)"
    }

}