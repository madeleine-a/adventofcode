package se.aoc

enum class Direction(val x: Int, val y: Int) {
    D(0, -1), U(0, 1), L(-1, 0), R(1, 0),
    VUR(1,1), VUL(-1,1),VDL(-1,-1), VDR(1,-1)
}