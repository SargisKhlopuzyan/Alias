package app.sargis.khlopuzyan.alias.ui.startGame

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.sargis.khlopuzyan.alias.helper.SingleLiveEvent
import app.sargis.khlopuzyan.alias.model.Game
import app.sargis.khlopuzyan.alias.model.Team
import app.sargis.khlopuzyan.alias.model.Word
import app.sargis.khlopuzyan.alias.repository.StartGameRepository

class StartGameViewModel constructor(private val startGameRepository: StartGameRepository) : ViewModel() {

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

    fun setGame(game: Game) {
        if (game.teams.isNotEmpty()) {
            game.currentPlayingTeam = game.teams[0]
        }
        game.round = 1

        for (word in words) {
            word.uuid = word.toString()
            for (team in game.teams) {
                team.words.add(word)
            }
        }

        gameLiveData.value = game
    }

}
