package app.sargis.khlopuzyan.alias.ui.startGame

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.sargis.khlopuzyan.alias.game.GameEngine
import app.sargis.khlopuzyan.alias.helper.SingleLiveEvent

class StartGameViewModel : ViewModel() {

    val showScoreLiveData: SingleLiveEvent<View> = SingleLiveEvent()
    val startLiveData: SingleLiveEvent<GameEngine> = SingleLiveEvent()

    val gameLiveData = MutableLiveData<GameEngine>()

    fun setupGameEngine(gameEngine: GameEngine?) {
        gameLiveData.value = gameEngine
    }

    /**
     * Handles Settings icon click
     * */
    fun onShowScoreClick(v: View) {
        showScoreLiveData.value = v
    }

    /**
     * Handles New Game icon click
     * */
    fun onStartClick() {
        startLiveData.value = gameLiveData.value
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
            gameLiveData.value = gameEngine
        }
    }
}