package app.sargis.khlopuzyan.alias.ui.gameSetup

import androidx.lifecycle.ViewModel
import app.sargis.khlopuzyan.alias.gameEngine.GameEngine
import app.sargis.khlopuzyan.alias.helper.SingleLiveEvent
import app.sargis.khlopuzyan.alias.model.GameType
import app.sargis.khlopuzyan.alias.repository.GameSetupRepository

class GameSetupViewModel constructor(private val gameSetupRepository: GameSetupRepository) :
    ViewModel() {

    var gameEngine: GameEngine = GameEngine()
    val startGameLiveData: SingleLiveEvent<GameEngine> = SingleLiveEvent()

    fun setupGameEngine() {

        if (gameEngine.settings == null) {
            gameEngine.settings = gameSetupRepository.loadSettings()
        }

        if (gameEngine.teams.isEmpty()) {
            gameEngine.availableTeams = gameSetupRepository.loadTeamNames().toMutableList()

            if (gameEngine.allAvailableWords.isNullOrEmpty()) {
                gameEngine.allAvailableWords = gameSetupRepository.loadWords().toMutableList()
            }

            gameEngine.settings?.let {
                for (i in 0 until it.teamsCount) {
                    if (gameEngine.availableTeams.isNotEmpty()) {
                        val team = gameEngine.availableTeams.removeAt(0)

                        team.words.clear()
                        for (word in gameEngine.allAvailableWords) {
                            team.words.add(word.copy())
                        }

                        gameEngine.teams.add(team)
                    }
                }
            }
        }

        gameEngine.round = 1

    }

    /**
     * Handles Settings icon click
     * */
    fun onClassicClick() {
        gameEngine.gameType = GameType.Classic
        resetGameEngine()
        startGameLiveData.value = gameEngine
    }

    /**
     * Handles New Game icon click
     * */
    fun onArcadeClick() {
        gameEngine.gameType = GameType.Arcade
        resetGameEngine()
        startGameLiveData.value = gameEngine
    }

    fun setNumberOfWords(numberOfWords: Int) {
        gameEngine.settings?.numberOfWords = numberOfWords
    }

    fun setRoundTime(roundTime: Int) {
        gameEngine.settings?.roundTime = roundTime
    }

    fun setGameSoundState(isEnabled: Boolean) {
        gameEngine.settings?.isGameSoundEnabled = isEnabled
    }

    fun setMissedWordPenaltyState(isEnabled: Boolean) {
        gameEngine.settings?.isMissedWordPenaltyEnabled = isEnabled
    }

    fun setGameWordsLanguage(language: String) {
        gameEngine.settings?.gameWordLanguage = language
    }

    fun setTranslateEnabledState(isEnabled: Boolean) {
        gameEngine.settings?.isWordTranslateEnabled = isEnabled
    }

    fun setTranslateLanguage(language: String) {
        gameEngine.settings?.wordTranslateLanguage = language
    }

    private fun resetGameEngine() {
        for (team in gameEngine.teams) {
            team.roundScores.clear()
            team.totalScore = 0
        }
        if (gameEngine.teams.isNotEmpty()) {
            gameEngine.currentPlayingTeam = gameEngine.teams[0]
        }
    }
}