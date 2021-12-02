package day02

import readInput

fun main() {
    var x = 0
    var y = 0
    var aim = 0

    fun processInstructions(movement: String, value: String) {
        val valueAsInt = value.toInt()
        when (movement) {
            "forward" -> x += valueAsInt
            "down" -> y += valueAsInt
            "up" -> y -= valueAsInt
        }
    }
    fun part1(input: List<String>): Int {
        x = 0
        y = 0

        for  (entry in input) {
            val split = entry.split(" ")
            val value = split[1]
            val movement = split[0]
            processInstructions(movement, value)
        }

        return x * y
    }

    fun processInstructionsWithAim(movement: String, value: String) {
        val valueAsInt = value.toInt()
        when (movement) {
            "forward" -> {
                x += valueAsInt
                y += valueAsInt * aim
            }
            "down" -> aim += valueAsInt
            "up" -> aim -= valueAsInt
        }
    }

    fun part2(input: List<String>): Int {
        x = 0
        y = 0
        aim = 0

        for  (entry in input) {
            val split = entry.split(" ")
            val value = split[1]
            val movement = split[0]
            processInstructionsWithAim(movement, value)
        }
        return x * y
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test", "day02")
    check(part1(testInput) == 150)

    val testInput2 = readInput("Day02_test", "day02")
    check(part2(testInput2) == 900)

    val input = readInput("Day02", "day02")
    println(part1(input))
    println(part2(input))


}
