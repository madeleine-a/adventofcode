package se.aoc

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class PointTest {

    @ParameterizedTest
    @CsvSource(
        "1,1,3,3,true",
        "5,7,7,5,true",
        "2,2,5,5,true",
        "2,8,4,7,false",
        "6,4,2,0,true",
        "5,5,8,2,true",
        "27,10,980,963,true"
    )
    fun isDiagonal(x: Int, y: Int, x2: Int, y2: Int, result: Boolean) {
        val p = Point(x, y)
        val p2 = Point(x2, y2)
        assertEquals(result, p.isDiagonal(p2))
    }

    @Test
    fun toRange(){
        val p = Point(7, 0)
        val p2 = Point(0, 0)
        assertEquals(8, p.toRange(p2).count())
    }
    @Test
    fun range(){
        val p = Point(27, 10)
        val p2 = Point(980, 963)
        assertEquals(954, p.toRange(p2).count())
    }

    @Test
    fun range2(){
        val p = Point(8, 0)
        val p2 = Point(0, 8)
        assertEquals(9, p.toRange(p2).count())
    }

    @Test
    fun range3(){
        val p = Point(0, 0)
        val p2 = Point(8, 8)
        assertEquals(9, p.toRange(p2).count())
    }
}