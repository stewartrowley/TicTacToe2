import java.util.*


private var gameBoard = Board(0)
private val player1 = "X"
private val player2 = "O"
private var currPlayer = ""
private var answer = ""

val size = 3

fun main() {
    val ANSI_BLUE = "\u001B[34m"
    println("Tic Tac Toe Game")
    val scanner = Scanner(System.`in`)

    println("Player 1, What is your name:")
    var player1Name = (scanner.nextLine())

    println("Player 2, What is your name:")
    var player2Name = (scanner.nextLine())

    println("$ANSI_BLUE$player1Name vs $ANSI_BLUE$player2Name")


    gameBoard = Board(size)
    gameBoard.printBoard()

    while (!gameBoard.isGameOver) {
        takeTurns()
        println("$currPlayer's turn")
        println("Enter row number:")
        var row = Integer.parseInt(scanner.nextLine())
        println("Enter column number:")
        var col = Integer.parseInt(scanner.nextLine())
        gameBoard.position(row - 1, col - 1, currPlayer)
        if (gameBoard.isGameOver) {
            println("Do you want to play again?")
            var answer = scanner.nextLine()
            if(isPlayingAgain(answer)) {
                gameBoard.resetGame()
            }
        }
    }
}

fun takeTurns() {
    currPlayer = if (player1 == currPlayer) {
        player2
    } else {
        player1
    }
}

fun isPlayingAgain(answer: String): Boolean {
    return (answer.equals("y", ignoreCase = true)
            || answer.equals("yes", ignoreCase = true))
}
