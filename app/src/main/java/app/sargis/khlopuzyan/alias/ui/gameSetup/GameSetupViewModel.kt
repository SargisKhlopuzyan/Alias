package app.sargis.khlopuzyan.alias.ui.gameSetup

import android.view.View
import androidx.lifecycle.ViewModel
import app.sargis.khlopuzyan.alias.helper.SingleLiveEvent
import app.sargis.khlopuzyan.alias.model.Game
import app.sargis.khlopuzyan.alias.model.GameType
import app.sargis.khlopuzyan.alias.model.Team
import app.sargis.khlopuzyan.alias.repository.GameSetupRepository

class GameSetupViewModel constructor(private val gameSetupRepository: GameSetupRepository) :
    ViewModel() {

    val game = Game()
    val startGameLiveData: SingleLiveEvent<Game> = SingleLiveEvent()

    init {
        val settings = gameSetupRepository.loadSettings()
        game.settings = settings
    }

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

    fun setNumberOfWords(numberOfWords: Int) {
        game.settings.numberOfWords = numberOfWords
    }

    fun setRoundTime(roundTime: Int) {
        game.settings.roundTime = roundTime
    }

    fun setGameSoundState(isEnabled: Boolean) {
        game.settings.isGameSoundEnabled = isEnabled
    }

    fun setMissedWordPenaltyState(isEnabled: Boolean) {
        game.settings.isMissedWordPenaltyEnabled = isEnabled
    }

    fun setGameWordsLanguage(language: String) {
        game.settings.gameWordLanguage = language
    }

    fun setTranslateEnabledState(isEnabled: Boolean) {
        game.settings.isWordTranslateEnabled = isEnabled
    }

    fun setTranslateLanguage(language: String) {
        game.settings.wordTranslateLanguage = language
    }

    //

    fun setTeam(teams: List<Team>) {
        game.teams = teams
    }

}