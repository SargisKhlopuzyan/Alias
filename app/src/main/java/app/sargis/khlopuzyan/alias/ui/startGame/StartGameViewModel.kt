package app.sargis.khlopuzyan.alias.ui.startGame

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.sargis.khlopuzyan.alias.helper.SingleLiveEvent
import app.sargis.khlopuzyan.alias.model.Game
import app.sargis.khlopuzyan.alias.model.Word
import app.sargis.khlopuzyan.alias.repository.StartGameRepository

class StartGameViewModel constructor(private val startGameRepository: StartGameRepository) :
    ViewModel() {

    val showScoreLiveData: SingleLiveEvent<View> = SingleLiveEvent()
    val startLiveData: SingleLiveEvent<Game> = SingleLiveEvent()

    val gameLiveData = MutableLiveData<Game>()

    var words: List<Word> = startGameRepository.loadWords()

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

    fun handleRoundFinish(game: Game) {
        if (game.teams.isNotEmpty()) {
            var nextPlayerIndex = game.teams.indexOf(game.currentPlayingTeam)
            if (nextPlayerIndex < 0) {
                nextPlayerIndex = 0
                game.round = 1
            } else {
                ++nextPlayerIndex
            }

            if (nextPlayerIndex == game.teams.size) {
                nextPlayerIndex = 0
                game.round = game.round + 1
            }

            game.currentPlayingTeam = game.teams[nextPlayerIndex]
            gameLiveData.value = game
        }
    }

}
