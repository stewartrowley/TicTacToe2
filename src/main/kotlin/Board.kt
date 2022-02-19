import kotlin.math.pow

class Board(private val num: Int) {
    private val empty = "__"
    private var moveCount = 0
    var isGameOver = false
    private var board = Array(num) { Array(num)  {empty} }
    val ANSI_BLUE = "\u001B[34m"


    fun resetBoard() {
        board = Array(num) { Array(num) {empty} }
    }

    fun printBoard() {
        board.forEach { column ->
            column.forEach { element ->
                if (element == empty) {
                    println("|$ANSI_BLUE$element|")
                } else {
                    println("| $ANSI_BLUE$element |")
                }
            }
            println()
        }
        println()
    }

    fun position(x: Int, y: Int, move: String) {
        if (!isGameOver &&
            isPositionValid(x, y)
            && board[x][y] == empty) {
            moveCount ++
            board[x][y] = move
            printBoard()
            isGameOver = isWinningMove(x, y, move) || isDraw()
            if (isGameOver && !isDraw()) {
                println("Your the winner!!!")
                println()
            } else if (isGameOver && isDraw()) {
                println("It is a draw...")
                println()
            }
        }
    }

    private fun isPositionValid(x: Int, y: Int): Boolean {
        return ((x in 0 until num ) && (y in 0 until num))
    }

    private fun isWinningMove(x: Int, y: Int, move: String): Boolean {
        // check the row
        for (i in 0 until num) {
            if (board[x][i] != move) {
                break
            }
            if (i == num - 1) {
                return true
            }
        }

        // check the column
        for (i in 0 until num) {
            if (board[i][y] != move) {
                break
            }
            if (i == num - 1) {
                return true
            }
        }

//        for(i in 0 until n) {
//            if (board[i][x] != move) {
//                break
//            }
//            if (board[i][y] != move) {
//                break
//            }
//            if (i == n - 1) {
//                return true
//            }
//
//        }

        // check diagonal
        if(x == y) {
            for (i in 0 until num ) {
                if (board[i][i] != move) {
                    break
                }
                if (i == num - 1) {
                    return true
                }
            }
        }

        // Check anti-diagonal
        if (x + y == num - 1) {
            for (i in 0 until num) {
                if (board[i][(num-1) - i] != move) {
                    break
                }
                if (i == num - 1) {
                    return true
                }
            }
        }
        return false
    }

    fun isDraw(): Boolean {
        return (moveCount == (num.toDouble().pow(2) - 1).toInt())
    }



    fun resetGame() {
        resetBoard()
        isGameOver = false
        moveCount = 0
    }
}