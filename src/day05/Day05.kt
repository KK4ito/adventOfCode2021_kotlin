package day05

import readInput
import kotlin.math.abs

fun main() {

    val currentDay = "Day05"
    val currentDayFolder = currentDay.lowercase()

    data class Point(val x: Int, val y: Int)

    fun markLinesWithPoint(beginPoint: Point, endPoint: Point, grid: Array<IntArray>) {

        if (beginPoint.x - endPoint.x == 0) {
            if (beginPoint.y > endPoint.y) {
                for (i in endPoint.y..beginPoint.y) {
                    grid[i][beginPoint.x] += 1
                }
            } else {
                for (i in beginPoint.y..endPoint.y) {
                    grid[i][beginPoint.x] += 1
                }
            }
        } else {
            if (beginPoint.x > endPoint.x) {
                for (i in endPoint.x..beginPoint.x) {
                    grid[beginPoint.y][i] += 1
                }
            } else {
                for (i in beginPoint.x..endPoint.x) {
                    grid[beginPoint.y][i] += 1
                }
            }
        }
    }

    fun part1(input: List<String>): Int {
        val wholeGrid = Array(1000) { IntArray(1000) { 0 } }

        for (entry in input) {
            val lineSplitted = entry.split(" -> ")
            val begin = lineSplitted[0].split(',')
            val end = lineSplitted[1].split(',')

            val beginX = begin[0]
            val beginY = begin[1]
            val endX = end[0]
            val endY = end[1]

            // process only if beginX == endX or beginY == endY
            if (beginX == endX || beginY == endY) {
                val beginPoint = Point(beginX.toInt(), beginY.toInt())
                val endPoint = Point(endX.toInt(), endY.toInt())
                markLinesWithPoint(beginPoint, endPoint, wholeGrid)
            }
        }
        return wholeGrid.flatMap { it.filter { innerIt -> innerIt >= 2 } }.size
    }

    fun markDiagonalLines(beginPoint: Point, endPoint: Point, grid: Array<IntArray>) {
        // process only 45 degrees
        if (abs(beginPoint.x - endPoint.x) == abs(beginPoint.y - endPoint.y)) {
            val difference = abs(beginPoint.x - endPoint.x)
            var beginIndexX = beginPoint.x
            var beginIndexY = beginPoint.y
            for (i in 0..difference) {
                grid[beginIndexY][beginIndexX] += 1
                if (beginPoint.x < endPoint.x) {
                    beginIndexX += 1
                } else {
                    beginIndexX -= 1
                }
                if (beginPoint.y < endPoint.y) {
                    beginIndexY += 1
                } else {
                    beginIndexY -= 1
                }
            }
        }
    }


    fun part2(input: List<String>): Int {
        val wholeGrid = Array(1000) { IntArray(1000) { 0 } }

        for (entry in input) {
            val lineSplitted = entry.split(" -> ")
            val begin = lineSplitted[0].split(',')
            val end = lineSplitted[1].split(',')

            val beginX = begin[0]
            val beginY = begin[1]
            val endX = end[0]
            val endY = end[1]
            val beginPoint = Point(beginX.toInt(), beginY.toInt())
            val endPoint = Point(endX.toInt(), endY.toInt())
            // process only if beginX == endX or beginY == endY
            if (beginX == endX || beginY == endY) {
                markLinesWithPoint(beginPoint, endPoint, wholeGrid)
            } else {
                markDiagonalLines(beginPoint, endPoint, wholeGrid)
            }

        }
        return wholeGrid.flatMap { it.filter { innerIt -> innerIt >= 2 } }.size

    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput(currentDay + "_test", currentDayFolder)
    check(part1(testInput) == 5)

    val testInput2 = readInput(currentDay + "_test", currentDayFolder)
    check(part2(testInput2) == 12)

    val input = readInput(currentDay, currentDayFolder)
    println(part1(input))
    println(part2(input))
    
}
