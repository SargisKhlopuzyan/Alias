package app.sargis.khlopuzyan.alias.ui.startGame

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.sargis.khlopuzyan.alias.gameEngine.GameEngine
import app.sargis.khlopuzyan.alias.helper.SingleLiveEvent

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

        Log.e("LOG_TAG", "handleRoundFinish")

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

    fun updateScore() {
        gameEngineLiveData.value = gameEngineLiveData.value
    }
}