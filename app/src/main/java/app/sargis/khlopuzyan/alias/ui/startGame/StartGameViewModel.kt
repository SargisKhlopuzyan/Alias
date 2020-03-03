package app.sargis.khlopuzyan.alias.ui.startGame

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.sargis.khlopuzyan.alias.helper.SingleLiveEvent
import app.sargis.khlopuzyan.alias.model.Game

class StartGameViewModel : ViewModel() {

    val showScoreLiveData: SingleLiveEvent<View> = SingleLiveEvent()
    val startLiveData: SingleLiveEvent<Game> = SingleLiveEvent()

    val gameLiveData = MutableLiveData<Game>()

    /**
     * Handles Settings icon click
     * */
    fun onShowScoreClick(v: View) {
        showScoreLiveData.value = v
    }

    /**
     * Handles New Game icon click
     * */
    fun onStartClick(v: View) {
        startLiveData.value = gameLiveData.value
    }

    fun setGame(game: Game) {
        if (game.teams.isNotEmpty()) {
            game.currentPlayingTeam = game.teams[0]
        }
        game.round = 1
        game.roundTimeRemaining = game.settings.roundTime

        gameLiveData.value = game
    }

}
