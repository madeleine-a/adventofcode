package se.aoc

import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.toDuration


fun <T> takeTime(block: () -> T): TimedValue<T> {
    val start = System.currentTimeMillis()
    val result = block()
    val end =  System.currentTimeMillis()
    return TimedValue(result, (end - start).toDuration(DurationUnit.MILLISECONDS))
}

data class TimedValue<T>(val value: T, val duration: Duration)