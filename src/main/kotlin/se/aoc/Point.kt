package se.aoc

class Point(var x: Int, var y: Int) {
    fun offset(aX: Int, anY: Int) {
            this.x += (aX);
            this.y += (anY);
    }

    var depth = 0
    fun aim(aX: Int, anY: Int) {
        this.x += (aX);
        this.y += (anY);
        if(aX > 0){
            this.depth += aX * this.y
        }
    }

    override fun toString(): String {
        return "Point(x=$x, y=$y)"
    }

}