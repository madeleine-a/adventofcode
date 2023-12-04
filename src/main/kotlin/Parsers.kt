import se.aoc.twentythree.isNumber

fun String.splitStrOfNumbersToSet(delimeter: String? = " ") = this.split(delimeter!!).mapNotNull { if (it.isNumber()) it.toInt() else null }.toSet()