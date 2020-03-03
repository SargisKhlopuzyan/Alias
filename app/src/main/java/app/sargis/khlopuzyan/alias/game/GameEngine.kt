package app.sargis.khlopuzyan.alias.game

import android.os.CountDownTimer

class GameEngine(
    val numberOfWords: Int,
    val roundTime: Int,
    val teamCounts: Int,
    val teamNames: List<String>,
    val gameRoundListener: GameRoundInterface
) {


    interface GameRoundInterface {
        fun roundEnded()
        fun gameFinished()
    }

    private var currentTeam: String = ""
    private val scores = mutableMapOf<String, Int>()

    init {

        if (teamNames.isNotEmpty()) {
            currentTeam = teamNames.first()

            for (teamName in teamNames) {
                scores[teamName] = 0
            }

        }
    }

    private fun isEnded() {

    }

    private val countDownTimer = object : CountDownTimer(roundTime.toLong(), 1000.toLong()) {

        override fun onFinish() {
            changePlayer()
            gameRoundListener.roundEnded()
        }

        override fun onTick(millisUntilFinished: Long) {

        }
    }

    fun startRound() {
        countDownTimer.start()
    }


    private fun changePlayer() {

        var currentPlayerIndex = teamNames.indexOf(currentTeam)

        ++currentPlayerIndex

        if (currentPlayerIndex >= teamNames.size) {
            currentPlayerIndex = 0
        }

        currentTeam = teamNames[currentPlayerIndex]

    }

    private fun isGameEnded() {
//        if ()
    }

}



