package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var PLAYER = true
    private var TURN_COUNT = 0

    var boardstatus = Array(3) { IntArray(3) }

    lateinit var board: Array<Array<Button>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        board = arrayOf(
            arrayOf(button, button2, button3),
            arrayOf(button4, button5, button6),
            arrayOf(button7, button8, button9)
        )

        for (i in board) {
            for (button in i) {
                button.setOnClickListener(this)
            }
        }
        initializeBoardStatus()

        reset.setOnClickListener() {
            PLAYER = true
            TURN_COUNT = 0
            display.text = "Player X Turn"
            initializeBoardStatus()
        }

    }

    private fun initializeBoardStatus() {
        for (i in 0..2) {
            for (j in 0..2) {
                boardstatus[i][j] = -1
            }
        }

        for (i in board) {
            for (button in i) {
                button.isEnabled = true
                button.text = ""
            }
        }
    }

    override fun onClick(view: View?) {
        if (view != null) {
            when (view.id) {
                R.id.button -> {
                    updateValue(row = 0, col = 0, player = PLAYER)
                }
                R.id.button2 -> {
                    updateValue(row = 0, col = 1, player = PLAYER)
                }
                R.id.button3 -> {
                    updateValue(row = 0, col = 2, player = PLAYER)
                }
                R.id.button4 -> {
                    updateValue(row = 1, col = 0, player = PLAYER)
                }
                R.id.button5 -> {
                    updateValue(row = 1, col = 1, player = PLAYER)
                }
                R.id.button6 -> {
                    updateValue(row = 1, col = 2, player = PLAYER)
                }
                R.id.button7 -> {
                    updateValue(row = 2, col = 0, player = PLAYER)
                }
                R.id.button8 -> {
                    updateValue(row = 2, col = 1, player = PLAYER)
                }
                R.id.button9 -> {
                    updateValue(row = 2, col = 2, player = PLAYER)
                }
            }
            TURN_COUNT++
            PLAYER = !PLAYER

            if (PLAYER) {
                updateDisplay("Player X Turn")
            } else {
                updateDisplay("Player 0 Turn")
            }
            if (TURN_COUNT == 9) {
                updateDisplay("Game Draw")
            }

            checkWinner()
        }
    }

    private fun checkWinner() {
        //horizonalRows
        for (i in 0..2) {
            if (boardstatus[i][0] == boardstatus[i][1] && boardstatus[i][1] == boardstatus[i][2]) {
                if (boardstatus[i][0] == 1) {
                    updateDisplay("Player X wins")
                    break
                } else if (boardstatus[i][0] == 0) {
                    updateDisplay("Player 0 wins")
                    break
                }
            }
        }

        //verticalcolumns
        for (i in 0..2) {
            if (boardstatus[0][i] == boardstatus[1][i] && boardstatus[1][i] == boardstatus[2][i]) {
                if (boardstatus[0][i] == 1) {
                    updateDisplay("Player X wins")
                    break
                } else if (boardstatus[0][i] == 0) {
                    updateDisplay("Player 0 wins")
                    break
                }
            }
        }

        //firstdiagonal
        if (boardstatus[0][0] == boardstatus[1][1] && boardstatus[0][0] == boardstatus[2][2]) {
            if (boardstatus[0][0] == 1) {
                updateDisplay("Player X wins")
            } else if (boardstatus[0][0] == 0) {
                updateDisplay("Player 0 wins")
            }
        }

        //seconddiagonal
        if (boardstatus[0][2] == boardstatus[1][1] && boardstatus[0][2] == boardstatus[2][0]) {
            if (boardstatus[0][2] == 1) {
                updateDisplay("Player X wins")
            } else if (boardstatus[0][2] == 0) {
                updateDisplay("Player 0 wins")
            }
        }
    }

    private fun updateDisplay(text: String) {
        display.text = text

        if (text.contains("wins", true)) {
            disableButton()
        }
    }

    private fun disableButton() {
        for (i in board) {
            for (button in i) {
                button.isEnabled = false
            }
        }
    }

    private fun updateValue(row: Int, col: Int, player: Boolean) {
        val text: String = if (player) "X" else "0"
        val value: Int = if (player) 1 else 0

        board[row][col].apply {
            isEnabled = false
            setText(text)
        }

        boardstatus[row][col] = value
    }


}