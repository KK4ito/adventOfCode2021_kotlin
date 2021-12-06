package day06

import readInput
import kotlin.math.abs

fun main() {

    val currentDay = "Day06"
    val currentDayFolder = currentDay.lowercase()

    fun decreaseOrReset(index: MutableListIterator<Int>): Boolean {
        //bruteforce, only for part 1
        if (index.hasNext()) {
            val day = index.next()
            if (day == 0) {
                index.set(6)
                index.add(8)
            } else {
                index.set(day - 1)
            }
            return true
        }
        return false
    }


    fun part1(input: List<String>): Int {
        val split = input[0].split(",")
        val fishList = split.map { it.toInt() } as MutableList
        for (i in 1..80) {
            val innerIter = fishList.listIterator()
            while (innerIter.hasNext()) {
                decreaseOrReset(innerIter)
            }
        }
        return fishList.size
    }

    fun tickMap(currentMap: Map<Int, Long>): Map<Int, Long> {
        val newMap = currentMap.mapKeys { (key, _) -> key - 1 }.toMutableMap()
        newMap[8] = newMap.getOrDefault(-1, 0)
        newMap[6] = newMap.getOrDefault(6, 0) + newMap.getOrDefault(-1, 0)
        newMap.remove(-1)
        return newMap
    }

    fun part2(input: List<String>): Long {
        // use map, with days left as key and fish occurrences as value
        val occurrences = input[0].split(",").map { it.toInt() }
            .groupingBy { it }
            .eachCount()
            .mapValues { (_, v) -> v.toLong() }
        var currentMap = occurrences
        for (i in 0..255) {
            currentMap = tickMap(currentMap)
        }
        return currentMap.values.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput(currentDay + "_test", currentDayFolder)
    check(part1(testInput) == 5934)

    val testInput2 = readInput(currentDay + "_test", currentDayFolder)
    check(part2(testInput2) == 26984457539)

    val input = readInput(currentDay, currentDayFolder)
    println(part1(input))
    println(part2(input))

}
