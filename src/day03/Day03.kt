package day03

import readInput

fun main() {

    val currentDay = "Day03"
    val currentDayFolder = currentDay.lowercase()

    fun part1(input: List<String>): Int {
        val lineLength = input[0].length
        var gammaRate = ""
        var epsilonRate = ""
        for (i in 0 until lineLength) {
            var one = 0
            var zero = 0
            input.map {
                when (it[i]) {
                    '0' -> zero += 1
                    '1' -> one += 1
                }
            }
            if (zero > one) {
                gammaRate += "0"
                epsilonRate += "1"
            } else {
                gammaRate += "1"
                epsilonRate += "0"
            }
        }
        val gammaAsDecimal = gammaRate.toInt(2)
        val epsilonAsDecimal = epsilonRate.toInt(2)
        return gammaAsDecimal * epsilonAsDecimal

    }


    fun part2(input: List<String>): Int {
        val lineLength = input[0].length
        var tempCollectionOxygenGeneratorRating = input
        var tempCollectionCO2ScrubberRating = input

        var finalOxy = 0;
        var finalCO2 = 0;

        for (i in 0 until lineLength) {

            if (finalOxy == 0) {
                var one = 0
                var zero = 0

                tempCollectionOxygenGeneratorRating.map {
                    when (it[i]) {
                        '0' -> zero += 1
                        '1' -> one += 1
                    }
                }

                if (zero > one) {
                    tempCollectionOxygenGeneratorRating = tempCollectionOxygenGeneratorRating.filter { it[i] == '0' }

                } else {
                    tempCollectionOxygenGeneratorRating = tempCollectionOxygenGeneratorRating.filter { it[i] == '1' }
                }
                if (tempCollectionOxygenGeneratorRating.size == 1) {
                    finalOxy = tempCollectionOxygenGeneratorRating[0].toInt(2)
                }
            }

            if (finalCO2 == 0) {
                var zeroCO2 = 0
                var oneC02 = 0

                tempCollectionCO2ScrubberRating.map {
                    when (it[i]) {
                        '0' -> zeroCO2 += 1
                        '1' -> oneC02 += 1
                    }
                }
                if (zeroCO2 > oneC02) {
                    tempCollectionCO2ScrubberRating = tempCollectionCO2ScrubberRating.filter { it[i] == '1' }

                } else {
                    tempCollectionCO2ScrubberRating = tempCollectionCO2ScrubberRating.filter { it[i] == '0'  }
                }
                if  (tempCollectionCO2ScrubberRating.size == 1) {
                    finalCO2 = tempCollectionCO2ScrubberRating[0].toInt(2)
                }
            }

        }

        return finalOxy * finalCO2
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput(currentDay + "_test", currentDayFolder)
    check(part1(testInput) == 198)

    val testInput2 = readInput(currentDay + "_test", currentDayFolder)
    check(part2(testInput2) == 230)

    val input = readInput("Day03", "day03")
    println(part1(input))
    println(part2(input))


}
