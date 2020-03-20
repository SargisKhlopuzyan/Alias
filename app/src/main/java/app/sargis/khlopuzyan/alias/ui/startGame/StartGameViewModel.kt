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
}