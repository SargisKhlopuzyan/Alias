package app.sargis.khlopuzyan.alias.ui.gameSetup

import android.view.View
import androidx.lifecycle.ViewModel
import app.sargis.khlopuzyan.alias.helper.SingleLiveEvent
import app.sargis.khlopuzyan.alias.model.Game
import app.sargis.khlopuzyan.alias.model.GameType
import app.sargis.khlopuzyan.alias.repository.GameSetupRepository

class GameSetupViewModel constructor(private val gameSetupRepository: GameSetupRepository) :
    ViewModel() {

    val startGameLiveData: SingleLiveEvent<Game> = SingleLiveEvent()

    val game = Game()

    /**
     * Handles Settings icon click
     * */
    fun onClassicClick(v: View) {
        game.gameType = GameType.Classic
        startGameLiveData.value = game
    }

    /**
     * Handles New Game icon click
     * */
    fun onArcadeClick(v: View) {
        game.gameType = GameType.Arcade
        startGameLiveData.value = game
    }

}