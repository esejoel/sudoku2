package com.example.sudoku

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import java.util.Random

class MainActivity : AppCompatActivity() {

    private val SIZE = 9
    private val board = Array(SIZE) { IntArray(SIZE) }
    private var selectedCell: Pair<Int, Int>? = null
    private lateinit var gridLayout: GridLayout
    private var errorCount = 0
    private var firstSelectionMade = false
    private var selectedBoardIndex = 0
    private var filledCells = 0 // Para determinar el número de celdas llenas según la dificultad

    // Variables para el cronómetro
    private var timeInSeconds = 0
    private val handler = Handler(Looper.getMainLooper())
    private lateinit var timerTextView: TextView
    private lateinit var errorCounterTextView: TextView
    private val cellLockStatus = Array(SIZE) { BooleanArray(SIZE) }
    private val lastValues = mutableListOf<Pair<Int, Int>>() // Para deshacer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gridLayout = findViewById(R.id.gridLayout)
        errorCounterTextView = findViewById(R.id.error_counter)
        timerTextView = findViewById(R.id.timer)

        showDifficultyDialog() // Mostrar el diálogo de selección de dificultad

        // Configurar botones de números
        for (num in 1..9) {
            val buttonId = resources.getIdentifier("button_$num", "id", packageName)
            val button = findViewById<Button>(buttonId)
            button.setOnClickListener {
                selectedCell?.let { cell ->
                    onNumberSelected(num, cell.first, cell.second)
                }
            }
        }

        // Botón Deshacer
        val undoButton = findViewById<Button>(R.id.button_undo)
        undoButton.setOnClickListener { resetBoard() }

        // Botón Borrar
        val deleteButton = findViewById<Button>(R.id.button_delete)
        deleteButton.setOnClickListener { deleteAction() }
    }

    private fun startTimer() {
        handler.post(object : Runnable {
            override fun run() {
                timeInSeconds++
                val minutes = timeInSeconds / 60
                val seconds = timeInSeconds % 60
                timerTextView.text = String.format("%02d:%02d", minutes, seconds)
                handler.postDelayed(this, 1000)
            }
        })
    }

    private fun initializeBoard() {
        selectedBoardIndex =
            Random().nextInt(SudokuBoards.solvedSudokuBoards.size) // Guardar el índice seleccionado
        val randomBoard = SudokuBoards.solvedSudokuBoards[selectedBoardIndex]

        for (i in 0 until SIZE) {
            for (j in 0 until SIZE) {
                board[i][j] = randomBoard[i][j]
                cellLockStatus[i][j] = true // Marcar celdas como bloqueadas inicialmente
            }
        }

        // Remover celdas según el nivel de dificultad
        removeCells()

        // Crear el tablero visible
        createBoard(gridLayout)
    }

    private fun removeCells() {
        val random = Random()
        var remainingCells =
            SIZE * SIZE - filledCells // Determinar cuántas celdas deben quedar vacías

        while (remainingCells > 0) {
            val row = random.nextInt(SIZE)
            val col = random.nextInt(SIZE)

            if (board[row][col] != 0) {
                board[row][col] = 0
                cellLockStatus[row][col] = false // Desbloquear las celdas eliminadas
                remainingCells--
            }
        }
    }

    private fun createBoard(gridLayout: GridLayout) {
        gridLayout.removeAllViews()
        val buttonSize =
            gridLayout.width / SIZE // Calcular el tamaño de los botones según el ancho del GridLayout

        for (i in 0 until SIZE) {
            for (j in 0 until SIZE) {
                val button = Button(this)
                button.text = if (board[i][j] == 0) "" else board[i][j].toString()
                val layoutParams = GridLayout.LayoutParams(
                    GridLayout.spec(i), GridLayout.spec(j)
                )
                layoutParams.width = buttonSize // Establecer el ancho del botón
                layoutParams.height = buttonSize // Establecer la altura del botón

                button.layoutParams = layoutParams
                button.background = ContextCompat.getDrawable(this, R.drawable.button_border)

                button.setOnClickListener {
                    selectCell(i, j, button)
                }

                gridLayout.addView(button)
            }
        }
    }


    private fun selectCell(row: Int, col: Int, button: Button) {
        if (!firstSelectionMade) {
            firstSelectionMade = true
            startTimer() // Iniciar el cronómetro en la primera selección
        }

        resetButtonStyles()
        selectedCell = Pair(row, col)
        button.setBackgroundColor(Color.parseColor("#B0E0E6"))

        for (r in 0 until SIZE) {
            for (c in 0 until SIZE) {
                val cellButton: Button = gridLayout.getChildAt(r * SIZE + c) as Button
                if ((r == row || c == col) && !(r == row && c == col)) {
                    cellButton.background =
                        ContextCompat.getDrawable(this, R.drawable.cell_translucent_background)
                }
            }
        }

        val startRow = (row / 3) * 3
        val startCol = (col / 3) * 3

        for (r in startRow until startRow + 3) {
            for (c in startCol until startCol + 3) {
                val cellButton: Button = gridLayout.getChildAt(r * SIZE + c) as Button
                if (r != row || c != col) {
                    cellButton.background =
                        ContextCompat.getDrawable(this, R.drawable.cell_translucent_background)
                }
            }
        }

        if (board[row][col] != 0) {
            // Verifica si hay otras celdas con el mismo número
            val selectedNum = board[row][col] // Usar el número de la celda seleccionada
            for (r in 0 until SIZE) {
                for (c in 0 until SIZE) {
                    // Verifica si hay otras celdas con el mismo número
                    if (board[r][c] == selectedNum && !(r == row && c == col)) {
                        val filledButton = gridLayout.getChildAt(r * SIZE + c) as Button
                        filledButton.setBackgroundColor(Color.parseColor("#B0E0E6")) // Celeste
                    }
                }
            }
        }
    }

    private fun resetButtonStyles() {
        for (i in 0 until SIZE) {
            for (j in 0 until SIZE) {
                val button = gridLayout.getChildAt(i * SIZE + j) as Button
                button.background = ContextCompat.getDrawable(this, R.drawable.button_border)
            }
        }
    }

    private fun onNumberSelected(num: Int, row: Int, col: Int) {
        if (cellLockStatus[row][col]) return
        board[row][col] = num
        val button = gridLayout.getChildAt(row * SIZE + col) as Button
        button.text = num.toString()

        checkNumberRepetition(num)

        val randomBoard =
            SudokuBoards.solvedSudokuBoards[selectedBoardIndex] // Usar el tablero seleccionado

        if (board[row][col] != randomBoard[row][col]) {
            errorCount++
            errorCounterTextView.text = "Errores: $errorCount/3"
            button.setBackgroundColor(Color.parseColor("#FFCCCB")) // Color de error
            // Verifica si hay otras celdas con el mismo número
            val selectedNum = board[row][col] // Usar el número de la celda seleccionada
            for (r in 0 until SIZE) {
                for (c in 0 until SIZE) {
                    // Verifica si hay otras celdas con el mismo número
                    if (board[r][c] == selectedNum && !(r == row && c == col)) {
                        val filledButton = gridLayout.getChildAt(r * SIZE + c) as Button
                        filledButton.setBackgroundColor(Color.parseColor("#B0E0E6")) // Celeste
                    }
                }
            }

            if (errorCount >= 3) {
                gameOver()
            }
        } else {
            // Cambiar el color de fondo de la celda actual
            button.setBackgroundColor(Color.parseColor("#98FB98")) // Color correcto

            // Verifica si hay otras celdas con el mismo número
            val selectedNum = board[row][col] // Usar el número de la celda seleccionada
            for (r in 0 until SIZE) {
                for (c in 0 until SIZE) {
                    // Verifica si hay otras celdas con el mismo número
                    if (board[r][c] == selectedNum && !(r == row && c == col)) {
                        val filledButton = gridLayout.getChildAt(r * SIZE + c) as Button
                        filledButton.setBackgroundColor(Color.parseColor("#B0E0E6")) // Celeste
                    }
                }
            }
        }

        checkNumberRepetition(num)
        // Verificar si el tablero está lleno
        if (isBoardFull()) {
            stopTimer() // Detener el cronómetro
            showCompletionDialog() // Mostrar el diálogo de finalización
        }
    }

    private fun resetBoard() {
        // Iterar sobre cada celda del tablero
        for (r in 0 until SIZE) {
            for (c in 0 until SIZE) {
                // Restablecer solo las celdas que no estaban bloqueadas (las que el usuario llenó)
                if (!cellLockStatus[r][c]) {
                    // Reiniciar el valor en el tablero a 0 (vacío)
                    board[r][c] = 0

                    // Restablecer el texto y el color de cada botón en el grid
                    val button = gridLayout.getChildAt(r * SIZE + c) as Button
                    button.text = ""
                }
            }
        }

        // Limpiar la lista de valores anteriores (acciones deshacer)
        lastValues.clear()
    }

    private fun deleteAction() {
        selectedCell?.let { cell ->
            if (!cellLockStatus[cell.first][cell.second]) {
                // Guardar el número que se va a borrar
                val deletedNumber = board[cell.first][cell.second]

                // Eliminar el número de la celda
                board[cell.first][cell.second] = 0
                val button = gridLayout.getChildAt(cell.first * SIZE + cell.second) as Button
                button.text = ""
                resetButtonStyles()
                button.setBackgroundColor(Color.parseColor("#B0E0E6"))

                // Restablecer el fondo de las celdas en la misma fila y columna
                for (r in 0 until SIZE) {
                    for (c in 0 until SIZE) {
                        val cellButton: Button = gridLayout.getChildAt(r * SIZE + c) as Button
                        if ((r == cell.first || c == cell.second) && !(r == cell.first && c == cell.second)) {
                            cellButton.background =
                                ContextCompat.getDrawable(this, R.drawable.cell_translucent_background)
                        }
                    }
                }

                // Restablecer el fondo de las celdas en el mismo cuadrante (3x3)
                val startRow = (cell.first / 3) * 3
                val startCol = (cell.second / 3) * 3

                for (r in startRow until startRow + 3) {
                    for (c in startCol until startCol + 3) {
                        val cellButton: Button = gridLayout.getChildAt(r * SIZE + c) as Button
                        if (r != cell.first || c != cell.second) {
                            cellButton.background =
                                ContextCompat.getDrawable(this, R.drawable.cell_translucent_background)
                        }
                    }
                }

                // Verificar las repeticiones del número eliminado
                if (deletedNumber != 0) {
                    checkNumberRepetition(deletedNumber) // Mandar el número eliminado a la función
                }
            }
        }
    }

    private fun gameOver() {
        // Detener el handler y eliminar mensajes
        handler.removeCallbacksAndMessages(null)

        // Bloquear todas las celdas del GridLayout
        for (r in 0 until SIZE) {
            for (c in 0 until SIZE) {
                val button = gridLayout.getChildAt(r * SIZE + c) as Button
                button.isEnabled = false // Deshabilitar la celda
            }
        }

        // Mostrar diálogo de "Juego Terminado"
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("¡Juego Terminado!")
        alertDialog.setMessage("Has alcanzado el límite de errores. ¿Quieres intentar de nuevo?")

        // Opción para reiniciar el juego
        alertDialog.setPositiveButton("Sí") { _, _ ->
            recreate() // Reiniciar la actividad para comenzar de nuevo
        }

        // Opción para salir
        alertDialog.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
            showGoodbyeDialog() // Mostrar otro AlertDialog
        }

        alertDialog.show()
    }


    private fun showGoodbyeDialog() {
        val goodbyeDialog = AlertDialog.Builder(this)
        goodbyeDialog.setTitle("¿¡EN SERIO!?")
        goodbyeDialog.setMessage("¡Gracias por jugar!")
        goodbyeDialog.setPositiveButton("Otra vez") { dialog, _ ->
            dialog.dismiss()
            recreate() // Reiniciar el juego aquí
        }
        goodbyeDialog.show()
    }

    private fun showDifficultyDialog() {
        val difficultyOptions = arrayOf("Fácil", "Normal", "Difícil")

        AlertDialog.Builder(this)
            .setTitle("Selecciona la dificultad")
            .setItems(difficultyOptions) { _, which ->
                filledCells = when (which) {
                    0 -> 40 // Fácil
                    1 -> 35 // Normal
                    2 -> 28 // Difícil
                    else -> 40 // Valor por defecto
                }
                initializeBoard()
            }
            .show()
    }

    private fun isBoardFull(): Boolean {
        for (i in 0 until SIZE) {
            for (j in 0 until SIZE) {
                if (board[i][j] == 0) {
                    return false // Si hay una celda vacía, el tablero no está lleno
                }
            }
        }
        return true // El tablero está lleno
    }

    private fun stopTimer() {
        handler.removeCallbacksAndMessages(null)
    }

    private fun showCompletionDialog() {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("¡Resuelto!")
        alertDialog.setMessage("Tu tiempo fue de: ${timerTextView.text}.")
        alertDialog.setPositiveButton("Juego Nuevo") { _, _ ->
            recreate() // Reiniciar la actividad para comenzar de nuevo
        }
        alertDialog.show()
    }

    private fun checkNumberRepetition(num: Int) {
        var count = 0

        // Contar cuántas veces aparece el número en el tablero
        for (r in 0 until SIZE) {
            for (c in 0 until SIZE) {
                if (board[r][c] == num) {
                    count++
                }
            }
        }

        // Encontrar el botón correspondiente al número
        val numberButtonId = resources.getIdentifier("button_$num", "id", packageName)
        val numberButton = findViewById<Button>(numberButtonId)

        // Si el número aparece 9 veces, bloquear el botón y cambiar el fondo a gris
        if (count == 9) {
            numberButton.isEnabled = false // Deshabilitar el botón
        } else {
            // Si el número aparece menos de 9 veces, habilitar el botón y restaurar el fondo
            numberButton.isEnabled = true
        }
    }
}
