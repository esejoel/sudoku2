package com.example.sudoku

object SudokuBoards {
    val solvedSudokuBoards = listOf(
        arrayOf(
            intArrayOf(8, 7, 4, 3, 9, 6, 1, 5, 2),
            intArrayOf(5, 6, 1, 2, 4, 7, 8, 9, 3),
            intArrayOf(3, 2, 9, 5, 8, 1, 7, 4, 6),
            intArrayOf(2, 3, 6, 7, 1, 9, 4, 8, 5),
            intArrayOf(4, 5, 7, 8, 3, 2, 9, 6, 1),
            intArrayOf(9, 1, 8, 6, 5, 4, 2, 3, 7),
            intArrayOf(1, 8, 2, 4, 6, 3, 5, 7, 9),
            intArrayOf(7, 4, 3, 9, 2, 5, 6, 1, 8),
            intArrayOf(6, 9, 5, 1, 7, 8, 3, 2, 4)
        ),
        arrayOf(
            intArrayOf(7, 5, 6, 3, 9, 1, 4, 8, 2),
            intArrayOf(2, 4, 1, 8, 5, 6, 9, 7, 3),
            intArrayOf(3, 8, 9, 7, 4, 2, 1, 6, 5),
            intArrayOf(5, 2, 8, 9, 1, 4, 7, 3, 6),
            intArrayOf(4, 1, 3, 5, 6, 7, 8, 2, 9),
            intArrayOf(6, 9, 7, 2, 8, 3, 5, 1, 4),
            intArrayOf(9, 6, 4, 1, 2, 8, 3, 5, 7),
            intArrayOf(8, 7, 2, 4, 3, 5, 6, 9, 1),
            intArrayOf(1, 3, 5, 6, 7, 9, 2, 4, 8)
        ),
        arrayOf(
            intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9),
            intArrayOf(4, 5, 6, 7, 8, 9, 1, 2, 3),
            intArrayOf(7, 8, 9, 1, 2, 3, 4, 5, 6),
            intArrayOf(2, 3, 4, 5, 6, 7, 8, 9, 1),
            intArrayOf(5, 6, 7, 8, 9, 1, 2, 3, 4),
            intArrayOf(8, 9, 1, 2, 3, 4, 5, 6, 7),
            intArrayOf(3, 4, 5, 6, 7, 8, 9, 1, 2),
            intArrayOf(6, 7, 8, 9, 1, 2, 3, 4, 5),
            intArrayOf(9, 1, 2, 3, 4, 5, 6, 7, 8)
        ),
        arrayOf(
            intArrayOf(3, 6, 2, 8, 5, 9, 7, 4, 1),
            intArrayOf(5, 7, 9, 4, 1, 6, 2, 3, 8),
            intArrayOf(4, 8, 1, 2, 3, 7, 5, 9, 6),
            intArrayOf(9, 2, 4, 6, 7, 1, 8, 5, 3),
            intArrayOf(6, 1, 3, 5, 8, 4, 9, 2, 7),
            intArrayOf(8, 5, 7, 9, 2, 3, 1, 6, 4),
            intArrayOf(2, 3, 5, 7, 6, 8, 4, 1, 9),
            intArrayOf(7, 9, 6, 1, 4, 2, 3, 8, 5),
            intArrayOf(1, 4, 8, 3, 9, 5, 6, 7, 2)
        ),
        arrayOf(
            intArrayOf(7, 5, 1, 8, 2, 3, 6, 4, 9),
            intArrayOf(9, 6, 2, 5, 4, 7, 3, 1, 8),
            intArrayOf(4, 8, 3, 1, 9, 6, 7, 2, 5),
            intArrayOf(3, 7, 9, 6, 1, 2, 8, 5, 4),
            intArrayOf(2, 1, 6, 4, 5, 8, 9, 3, 7),
            intArrayOf(8, 4, 5, 7, 3, 9, 2, 6, 1),
            intArrayOf(5, 9, 8, 3, 6, 4, 1, 7, 2),
            intArrayOf(6, 2, 4, 9, 7, 1, 5, 8, 3),
            intArrayOf(1, 3, 7, 2, 8, 5, 4, 9, 6)
        ),
        arrayOf(
            intArrayOf(5, 3, 4, 6, 7, 8, 9, 1, 2),
            intArrayOf(6, 7, 2, 1, 9, 5, 3, 4, 8),
            intArrayOf(1, 9, 8, 3, 4, 2, 5, 6, 7),
            intArrayOf(8, 5, 9, 7, 6, 1, 4, 2, 3),
            intArrayOf(4, 2, 6, 8, 5, 3, 7, 9, 1),
            intArrayOf(7, 1, 3, 9, 2, 4, 8, 5, 6),
            intArrayOf(9, 6, 1, 5, 3, 7, 2, 8, 4),
            intArrayOf(2, 8, 7, 4, 1, 9, 6, 3, 5),
            intArrayOf(3, 4, 5, 2, 8, 6, 1, 7, 9)
        ),
        arrayOf(
            intArrayOf(5, 1, 6, 8, 4, 7, 9, 2, 3),
            intArrayOf(4, 9, 3, 1, 2, 5, 7, 8, 6),
            intArrayOf(8, 2, 7, 3, 9, 6, 5, 1, 4),
            intArrayOf(1, 6, 8, 7, 5, 3, 2, 4, 9),
            intArrayOf(3, 4, 9, 2, 8, 1, 6, 7, 5),
            intArrayOf(2, 7, 5, 4, 6, 9, 1, 3, 8),
            intArrayOf(9, 3, 2, 5, 1, 4, 8, 6, 7),
            intArrayOf(7, 5, 1, 6, 3, 8, 4, 9, 2),
            intArrayOf(6, 8, 4, 9, 7, 2, 3, 5, 1)
        ),
        arrayOf(
            intArrayOf(2, 3, 5, 9, 7, 4, 8, 1, 6),
            intArrayOf(7, 4, 6, 2, 8, 1, 3, 5, 9),
            intArrayOf(9, 1, 8, 5, 6, 3, 4, 7, 2),
            intArrayOf(5, 8, 2, 1, 9, 7, 6, 4, 3),
            intArrayOf(3, 9, 4, 6, 5, 8, 1, 2, 7),
            intArrayOf(6, 7, 1, 4, 3, 2, 5, 9, 8),
            intArrayOf(1, 6, 7, 8, 4, 9, 2, 3, 5),
            intArrayOf(8, 2, 3, 7, 1, 5, 9, 6, 4),
            intArrayOf(4, 5, 9, 3, 2, 6, 7, 8, 1)
        )
    )

    fun findDuplicateBoards() {
        val duplicates = mutableListOf<Pair<Int, Int>>()

        for (i in solvedSudokuBoards.indices) {
            for (j in i + 1 until solvedSudokuBoards.size) {
                if (areBoardsEqual(solvedSudokuBoards[i], solvedSudokuBoards[j])) {
                    duplicates.add(Pair(i + 1, j + 1)) // Usar i + 1 para la numeración
                }
            }
        }

        if (duplicates.isEmpty()) {
            println("No hay tableros duplicados.")
        } else {
            println("Tableros duplicados encontrados:")
            duplicates.forEach { (index1, index2) ->
                println("Tablero $index1 es igual al Tablero $index2")
            }
        }
    }

    private fun areBoardsEqual(board1: Array<IntArray>, board2: Array<IntArray>): Boolean {
        return board1.contentDeepEquals(board2)
    }

    fun isValidSudoku(board: Array<IntArray>): Boolean {
        // Verificar filas y columnas
        for (i in 0 until 9) {
            val rowCheck = BooleanArray(9) // Para verificar filas
            val colCheck = BooleanArray(9) // Para verificar columnas

            for (j in 0 until 9) {
                // Verificar fila
                if (board[i][j] != 0) {
                    if (rowCheck[board[i][j] - 1]) return false // Duplicado en fila
                    rowCheck[board[i][j] - 1] = true
                }

                // Verificar columna
                if (board[j][i] != 0) {
                    if (colCheck[board[j][i] - 1]) return false // Duplicado en columna
                    colCheck[board[j][i] - 1] = true
                }
            }
        }

        // Verificar subcuadros 3x3
        for (row in 0 until 9 step 3) {
            for (col in 0 until 9 step 3) {
                val boxCheck = BooleanArray(9) // Para verificar el subcuadro
                for (i in 0 until 3) {
                    for (j in 0 until 3) {
                        val num = board[row + i][col + j]
                        if (num != 0) {
                            if (boxCheck[num - 1]) return false // Duplicado en el subcuadro
                            boxCheck[num - 1] = true
                        }
                    }
                }
            }
        }

        return true // El tablero es válido
    }
}

fun main() {
    // Verificar si los tableros son válidos
    SudokuBoards.solvedSudokuBoards.forEachIndexed { index, board ->
        if (SudokuBoards.isValidSudoku(board)) {
            println("El Tablero ${index + 1} es válido.")
        } else {
            println("El Tablero ${index + 1} NO es válido.")
        }
    }

    // Buscar tableros duplicados
    SudokuBoards.findDuplicateBoards()
}
