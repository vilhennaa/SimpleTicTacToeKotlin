fun main() {
    val board = Array(3) { CharArray(3) { ' ' } }
    var currentPlayer = 'X'

    printBoard(board)

    while (true) {
        val (row, col) = getPlayerMove(board, currentPlayer)

        board[row][col] = currentPlayer
        printBoard(board)

        if (checkWin(board, currentPlayer)) {
            println("$currentPlayer wins")
            break
        }

        if (isBoardFull(board)) {
            println("Draw")
            break
        }

        currentPlayer = if (currentPlayer == 'X') 'O' else 'X'
    }
}

fun printBoard(board: Array<CharArray>) {
    println("---------")
    for (i in board.indices) {
        println("| ${board[i].joinToString(" ")} |")
    }
    println("---------")
}

fun getPlayerMove(board: Array<CharArray>, currentPlayer: Char): Pair<Int, Int> {
    while (true) {
        print("> ")
        val input = readLine() ?: ""
        val (row, col) = try {
            val (r, c) = input.split(" ").map { it.toInt() - 1 }
            if (r !in 0..2 || c !in 0..2) {
                println("Coordinates should be from 1 to 3!")
                continue
            }
            if (board[r][c] != ' ') {
                println("This cell is occupied! Choose another one!")
                continue
            }
            r to c
        } catch (e: NumberFormatException) {
            println("You should enter numbers!")
            continue
        } catch (e: IndexOutOfBoundsException) {
            println("Coordinates should be from 1 to 3!")
            continue
        }
        return row to col
    }
}

fun checkWin(board: Array<CharArray>, player: Char): Boolean {
    for (i in 0 until 3) {
        if (board[i][0] == player && board[i][1] == player && board[i][2] == player) return true
        if (board[0][i] == player && board[1][i] == player && board[2][i] == player) return true
    }
    if (board[0][0] == player && board[1][1] == player && board[2][2] == player) return true
    if (board[0][2] == player && board[1][1] == player && board[2][0] == player) return true
    return false
}

fun isBoardFull(board: Array<CharArray>): Boolean {
    for (row in board) {
        for (cell in row) {
            if (cell == ' ') return false
        }
    }
    return true
}
