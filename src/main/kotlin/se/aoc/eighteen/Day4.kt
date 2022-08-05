package se.aoc.eighteen

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoField

class Day4 {
    private var schedule = mutableMapOf<Int, GuardSchedule>()
    fun run() {
        val input = Day4::class.java.getResource("/eighteen/day4.txt").readText()
        val list = input.split("\r\n")
        println("Part 1")
        println(part1(list))
        println("Part 2")
        println(part2(list))
    }

    fun init(input: List<String>) {
        val list = input.sorted()
        var id = 0
        for (i in list.indices) {
            if (isGuard(list[i])) {
                id = list[i].substringAfter("]").filter { it.isDigit() }.toInt()
                val guard = GuardSchedule(id)
                if (schedule[id] == null) {
                    schedule[id] = guard
                }
            }
            schedule[id]?.addSchedule(toSchedule(list[i]))
        }
    }

    fun part1(input: List<String>): Number {
        if (schedule.isEmpty()) {
            init(input)
        }
        var l = schedule.values.toList()
        l = l.sortedByDescending { it.getTotalSleep() }

        return l.first().getSleepiestMinute() * l.first().id
    }

    fun part2(input: List<String>): Int {
        if (schedule.isEmpty()) {
            init(input)
        }
        var l = schedule.values.toList()
        l = l.sortedByDescending { it.getCountOfSleepiestMinute() }
        return l.first().getSleepiestMinute() * l.first().id
    }

    private fun isGuard(str: String): Boolean {
        return str.contains("Guard")
    }

    private fun toSchedule(str: String): Schedule {
        val list = str.replace("[", "").split("]").map { it.trim() }
        val time = LocalDateTime.parse(list[0], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
        return Schedule(time, ScheduleEnum.fromValue(list[1]))
    }

}

enum class ScheduleEnum {
    BEGIN, FALLS_ASLEEP, WAKE_UP, OHH;

    companion object {
        fun fromValue(str: String): ScheduleEnum {

            return when (str.filter { !it.isDigit() }) {
                "falls asleep" -> FALLS_ASLEEP
                "wakes up" -> WAKE_UP
                "Guard # begins shift" -> BEGIN
                else -> OHH
            }
        }
    }
}

data class GuardSchedule(val id: Int) {
    var scheduleList = mutableListOf<Schedule>()
    fun addSchedule(schedule: Schedule) {
        this.scheduleList.add(schedule)
    }

    fun getCountOfSleepiestMinute(): Int {
        val s = getSleepList().map { IntRange((it.last()), it.first() - 1).toList() }
            .flatten().groupingBy { it }.eachCount().maxByOrNull { it.value }
        return s?.value ?: 0
    }

    fun getSleepiestMinute(): Int {
        val s = getSleepList().map { IntRange((it.last()), it.first() - 1).toList() }
            .flatten().groupingBy { it }.eachCount().maxByOrNull { it.value }
        return s!!.key
    }

    private fun getSleepList(): List<List<Int>> {
        return scheduleList.filter { it.enum != ScheduleEnum.BEGIN }.reversed()
            .windowed(2, 2).map { list -> list.map { it.getMinutes() } }
    }

    fun getTotalSleep(): Int {
        var sleep = 0
        getSleepList().forEach { list -> sleep += list.first() - list.last() }
        return sleep
    }

    override fun toString(): String {
        return "GuardSchedule(id=$id, scheduleList=$scheduleList), totalSleep=${getTotalSleep()}"
    }

}

data class Schedule(val time: LocalDateTime, val enum: ScheduleEnum) {
    fun getMinutes(): Int {
        return time.get(ChronoField.MINUTE_OF_HOUR)
    }
}