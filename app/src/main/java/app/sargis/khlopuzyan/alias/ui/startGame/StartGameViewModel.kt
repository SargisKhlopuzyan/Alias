package app.sargis.khlopuzyan.alias.ui.startGame

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.sargis.khlopuzyan.alias.gameEngine.GameEngine
import app.sargis.khlopuzyan.alias.helper.SingleLiveEvent
import app.sargis.khlopuzyan.alias.model.Team

class StartGameViewModel : ViewModel() {

    val showScoreLiveData: SingleLiveEvent<View> = SingleLiveEvent()
    val startLiveData: SingleLiveEvent<GameEngine> = SingleLiveEvent()
    val gameFinishedLiveData: SingleLiveEvent<GameEngine> = SingleLiveEvent()

    val gameEngineLiveData = MutableLiveData<GameEngine>()

    fun setupGameEngine(gameEngine: GameEngine?) {
        gameEngineLiveData.value = gameEngine
    }

    /**
     * Handles Settings icon click
     * */
    fun onShowScoreClick(v: View) {
        updateScore()
        showScoreLiveData.value = v
    }

    /**
     * Handles New Game icon click
     * */
    fun onStartClick() {
        startLiveData.value = gameEngineLiveData.value
    }

    fun handleRoundFinish(gameEngine: GameEngine) {

        if (gameEngine.teams.isNotEmpty()) {

            var nextPlayerIndex = gameEngine.teams.indexOf(gameEngine.currentPlayingTeam)

            if (nextPlayerIndex < 0) {
                nextPlayerIndex = 0
                gameEngine.round = 1
            } else {
                ++nextPlayerIndex
            }

            if (nextPlayerIndex == gameEngine.teams.size) {
                nextPlayerIndex = 0
                gameEngine.round = gameEngine.round + 1
            }

            gameEngine.currentPlayingTeam = gameEngine.teams[nextPlayerIndex]
            gameEngineLiveData.value = gameEngine
        }

        gameEngine.settings?.let {

            if (gameEngine.teams.isNullOrEmpty() ||
                gameEngine.teams.size < 2 ||
                gameEngine.teams.first().roundScores.size != gameEngine.teams.last().roundScores.size
            ) {
                return
            }

            var isGameFinished = false

            for (team in gameEngine.teams) {
                if (team.totalScore >= it.numberOfWords) {
                    isGameFinished = true
                    break
                }
            }

            if (isGameFinished) {
                gameFinishedLiveData.value = gameEngine
            }
        }
    }

    fun getRoundCount(): Int {
        return gameEngineLiveData.value?.round ?: 1
    }

    fun getTeamsCount(): Int {
        return gameEngineLiveData.value?.teams?.count() ?: 0
    }

    private fun updateScore() {
        gameEngineLiveData.value = gameEngineLiveData.value
    }

    fun getTeams(): MutableList<Team>? {
        return gameEngineLiveData.value?.teams
    }

    fun resetGameEngine() {
        gameEngineLiveData.value?.let {
            it.round = 1
            it.isGameFinished = false
            it.winnerTeam = null
            for (team in it.teams) {
                team.totalScore = 0
                team.roundScores.clear()
//            team.words.clear()
//            team.words
            }
        }
    }
}