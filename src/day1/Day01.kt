package day1

import readInput

fun main() {
    fun part1(input: List<String>): Int {
//        var cnt = 0;
//        for (i in input.indices) {
//            if (i == 0) continue;
//
//            val currentValue = input[i].toInt();
//            val previousValue = input[i-1].toInt();
//
//            if (currentValue > previousValue) cnt++;
//        }
//        return cnt
        val inputAsInt = input.map { it.toInt() }
        return inputAsInt.windowed(2).count { (x, y) -> y > x }
    }

    fun part2(input: List<String>): Int {
//        var cnt = 0;
//        for (i in input.indices) {
//            if (i + 3 >= input.size) return cnt;
//
//            val currentValue = input[i].toInt();
//            val nextValue = input[i+1].toInt();
//            val nextNextValue = input[i+2].toInt();
//            val firstValue = currentValue + nextValue + nextNextValue;
//
//            val currentValueSecondHalf = input[i+1].toInt();
//            val nextValueSecondHalf = input[i+2].toInt();
//            val nextNextValueSecondHalf = input[i+3].toInt();
//            val secondValue = currentValueSecondHalf + nextValueSecondHalf + nextNextValueSecondHalf;
//
//            if (secondValue > firstValue) cnt++;
//        }
//        return cnt
        val inputAsInt = input.map { it.toInt() }
        return inputAsInt.windowed(3).map { it.sum() }.windowed(2).count { (x, y) -> y > x }

    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test", "day1")
    check(part1(testInput) == 7)

    val testInput2 = readInput("Day01_test", "day1")
    check(part2(testInput2) == 5)

    val input = readInput("Day01", "day1")
    println(part1(input))
    println(part2(input))


}
