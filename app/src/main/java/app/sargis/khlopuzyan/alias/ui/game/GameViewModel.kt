package app.sargis.khlopuzyan.alias.ui.game

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.sargis.khlopuzyan.alias.helper.SingleLiveEvent
import app.sargis.khlopuzyan.alias.model.Game

class GameViewModel : ViewModel() {

    val closeLiveData: SingleLiveEvent<View> = SingleLiveEvent()
    val skipLiveData: SingleLiveEvent<View> = SingleLiveEvent()

    val game = MutableLiveData<Game>(Game())

    /**
     * Handles Settings icon click
     * */
    fun onCloseClick(v: View) {
        closeLiveData.value = v
    }

    /**
     * Handles New Game icon click
     * */
    fun onSkipClick(v: View) {
        skipLiveData.value = v
    }

    /**
     * Handles New Game icon click
     * */
    fun onDoneClick(v: View) {
        skipLiveData.value = v
    }

    fun setGame(_game: Game) {
        if (_game.teams.isNotEmpty()) {
            _game.currentPlayingTeam = _game.teams[0]
        }
        _game.round = 1
        _game.roundTimeRemaining = _game.settings.roundTime
        game.value = _game
    }

}