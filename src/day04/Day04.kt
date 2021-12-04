package day04

import readInput

fun main() {

    data class Square(val value: Int, var marked: Boolean)

    fun parseBoards(input: List<String>): List<List<List<Square>>> =
        input.drop(1)
            .filter { it.isNotEmpty() }
            .chunked(5)
            .map {
                it.map { str ->
                    str.split(" ").filter { entry -> entry.isNotEmpty() }.map { c -> Square(c.toInt(), false) }
                }
            }

    fun sumUnmarked(board: List<List<Square>>): Int {
        return board.flatMap { it.filter { entry -> !entry.marked } }.sumOf { it.value }
    }

    fun putNumber(board: List<List<Square>>, n: Int): Int? {
        for (i in board.indices) {
            for (j in 0 until board[i].size) {
                val x = board[i][j]
                if (x.value == n) {
                    x.marked = true

                    val row = board[i].map { it.marked }.reduce(Boolean::and)
                    if (row) return n * sumUnmarked(board)

                    val column =
                        board.map { it.filterIndexed { index, _ -> index == j }.first() }
                            .map { it.marked }.reduce(Boolean::and)

                    if (column) return n * sumUnmarked(board)
                }
            }
        }

        return null
    }

    fun part1(input: List<String>): Int {
        val numbers = input.first().split(",").map { it.toInt() }
        val boards = parseBoards(input)

        for (n in numbers) {
            for (board in boards) {
                for (i in board.indices) {
                    for (j in board.indices) {
                        val x = board[i][j]
                        if (x.value == n) {
                            x.marked = true

                            // check row
                            val row = board[i].map { it.marked }.reduce(Boolean::and)
                            if (row) return n * sumUnmarked(board)

                            // check column
                            val column =
                                board.map { it.filterIndexed { index, _ -> index == j }.first() }
                                    .map { it.marked }.reduce(Boolean::and)

                            if (column) return n * sumUnmarked(board)
                        }
                    }
                }
            }
        }

        return 0
    }


    fun part2(input: List<String>): Int {
        data class Player(val board: List<List<Square>>, var playing: Boolean)

        val numbers = input.first().split(",").map { it.toInt() }
        val boards = parseBoards(input).toMutableList()
        val boardsTotal = boards.size

        val players = boards.map { Player(it, true) }

        var winsCounter = 0
        for (n in numbers) {
            players.filter { it.playing }.forEach { player ->
                val winningResult = putNumber(player.board, n)
                if (winningResult != null) {
                    player.playing = false
                    winsCounter++

                    if (winsCounter == boardsTotal) return winningResult
                }
            }
        }
        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test", "day04")
    check(part1(testInput) == 4512)

    val testInput2 = readInput("Day04_test", "day04")
    check(part2(testInput2) == 1924)

    val input = readInput("Day04", "day04")
    println(part1(input))
    println(part2(input))


}
