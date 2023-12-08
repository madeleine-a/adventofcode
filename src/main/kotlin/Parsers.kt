import se.aoc.twentythree.isNumber

fun String.splitStrOfNumbersToSet(delimiter: String? = " ") = this.split(delimiter!!).mapNotNull { if (it.isNumber()) it.toInt() else null }.toSet()
fun String.splitStrOfNumbersToList(delimiter: String? = " ") = this.split(delimiter!!).mapNotNull { if(it.isNumber())it.toLong() else null }.toList()