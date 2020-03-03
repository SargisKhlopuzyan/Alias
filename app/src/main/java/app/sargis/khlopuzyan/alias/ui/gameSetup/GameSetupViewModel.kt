package app.sargis.khlopuzyan.alias.ui.gameSetup

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.sargis.khlopuzyan.alias.helper.SingleLiveEvent
import app.sargis.khlopuzyan.alias.model.Game
import app.sargis.khlopuzyan.alias.model.GameType
import app.sargis.khlopuzyan.alias.model.Settings
import app.sargis.khlopuzyan.alias.model.Team
import app.sargis.khlopuzyan.alias.repository.GameSetupRepository

class GameSetupViewModel constructor(private val gameSetupRepository: GameSetupRepository) :
    ViewModel() {

    val startGameLiveData: SingleLiveEvent<Game> = SingleLiveEvent()

    val game = Game()
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
        Log.e("LOG_TAG", "setNumberOfWords")
    }

    fun setRoundTime(roundTime: Int) {
        game.settings.roundTime = roundTime
        Log.e("LOG_TAG", "setRoundTime")
    }

    fun setGameSoundState(isEnabled: Boolean) {
        game.settings.isGameSoundEnabled =isEnabled
        Log.e("LOG_TAG", "setGameSoundState")
    }

    fun setMissedWordPenaltyState(isEnabled: Boolean) {
        game.settings.isMissedWordPenaltyEnabled = isEnabled
        Log.e("LOG_TAG", "setMissedWordPenaltyState")
    }

    fun setGameWordsLanguage(language: String) {
        game.settings.gameWordLanguage = language
        Log.e("LOG_TAG", "setGameWordsLanguage")
    }

    fun setTranslateEnabledState(isEnabled: Boolean) {
        game.settings.isWordTranslateEnabled = isEnabled
        Log.e("LOG_TAG", "setTranslateEnabledState")
    }

    fun setTranslateLanguage(language: String) {
        game.settings.wordTranslateLanguage = language
        Log.e("LOG_TAG", "setTranslateLanguage")
    }

    //

    fun setTeam(teams: List<Team>) {
        game.teams = teams
        Log.e("LOG_TAG", "setTeam")

    }


}