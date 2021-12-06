package se.aoc.twentyone

class Day6 {

    fun run() {
        val input = Day6::class.java.getResource("/twentyone/day6.txt").readText()
        println("Part 1")
        println(part1(input))

        println("Part 2")
        println(part2(input))
    }

    fun part1(input: String): Number {
        return work(input.split(",").map { it.toInt() }, 80)
    }

    fun part2(input: String): Long {
        return work(input.split(",").map { it.toInt() }, 256)
    }

    private fun work(list: List<Int>, days: Int): Long {
        var numberMap = initMap(list)
        for (i in 1..days) {
            var map2 = initMap(null)
            for(key in numberMap.keys.reversed()){
                if (numberMap[key]!! > 0 && key > 0) {
                    map2[key-1] = numberMap[key]!!
                } else if(key == 0 && numberMap[key]!! > 0){
                    map2[8] = numberMap[key]!!
                    map2[6] = numberMap[7]!! + numberMap[key]!!
                }
            }
            numberMap = map2.toMutableMap()
        }
        return numberMap.values.sum()
    }

    private fun initMap(list: List<Int>?): MutableMap<Int, Long>{
        var map = mutableMapOf<Int, Long>()
        for(i in 0..8){
            map[i]=0
        }
        if (list != null) {
            for(j in list){
                if(map[j] != null){
                    map[j] = map[j]!! +1
                }
            }
        }
        return map
    }
}


