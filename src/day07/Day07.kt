package day07

import readInput
import kotlin.math.abs

fun main() {

    val currentDay = "Day07"
    val currentDayFolder = currentDay.lowercase()


    fun part1(input: List<String>): Int {
        val crabHorizontal = input[0].split(",").map { it.toInt() }
        val min = crabHorizontal.minOrNull()!!
        val max = crabHorizontal.maxOrNull()!!

        var wholeCrabCost = Int.MAX_VALUE;
        for (i in min..max) {
            val currentCost = crabHorizontal.sumOf { singleCrab -> abs(singleCrab - i ) }
            if (currentCost < wholeCrabCost) {
                wholeCrabCost = currentCost
            }
        }
        return wholeCrabCost
    }

    fun part2(input: List<String>): Int {
        val crabHorizontal = input[0].split(",").map { it.toInt() }
        val min = crabHorizontal.minOrNull()!!
        val max = crabHorizontal.maxOrNull()!!

        var wholeCrabCost = Int.MAX_VALUE;
        for (i in min..max) {
            val currentCost = crabHorizontal.sumOf { singleCrab ->
                val n =  abs(singleCrab - i )
                // gaussche summenformel
                 (n * (n + 1) ) / 2
            }
            if (currentCost < wholeCrabCost) {
                wholeCrabCost = currentCost
            }
        }
        return wholeCrabCost
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput(currentDay + "_test", currentDayFolder)
    check(part1(testInput) == 37)

    val testInput2 = readInput(currentDay + "_test", currentDayFolder)
    check(part2(testInput2) == 168)

    val input = readInput(currentDay, currentDayFolder)
    println(part1(input))
    println(part2(input))

}
