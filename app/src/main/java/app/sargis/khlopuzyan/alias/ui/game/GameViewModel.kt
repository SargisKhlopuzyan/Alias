package app.sargis.khlopuzyan.alias.ui.game

import android.os.CountDownTimer
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.sargis.khlopuzyan.alias.gameEngine.GameEngine
import app.sargis.khlopuzyan.alias.helper.SingleLiveEvent
import app.sargis.khlopuzyan.alias.model.GameType
import app.sargis.khlopuzyan.alias.model.Word

class GameViewModel : ViewModel() {

    val closeLiveData: SingleLiveEvent<View> = SingleLiveEvent()
    val skipLiveData: SingleLiveEvent<View> = SingleLiveEvent()

    val gameEngineLiveData = MutableLiveData(GameEngine())

    val roundFinishedLiveData = MutableLiveData<GameEngine>()
    val remainingRoundTimeLiveData = MutableLiveData(0)
    val roundScoreLiveData = MutableLiveData(0)
    val totalScoreLiveData = MutableLiveData(0)

    var words = mutableListOf<Word>()
    private var selectedCount = 0
    private var timer: CountDownTimer? = null

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
        gameEngineLiveData.value?.let {

            it.settings?.let { setting ->
                if (setting.isMissedWordPenaltyEnabled) {

                    var roundScore = it.currentPlayingTeam?.roundScores?.get(it.round) ?: 0
                    val itemCount = if (it.gameType == GameType.Classic) 5 else 1
                    val skippedWordsCount = itemCount - selectedCount

                    roundScore -= skippedWordsCount

                    it.currentPlayingTeam?.roundScores?.set(it.round, roundScore)
                    roundScoreLiveData.value = roundScore

                    var totalScore = it.currentPlayingTeam?.totalScore ?: 0
                    totalScore -= skippedWordsCount

                    it.currentPlayingTeam?.totalScore = totalScore
                    totalScoreLiveData.value = totalScore
                }
            }

            generateRandomWordsList()
        }
    }

    /**
     * Handles New Game icon click
     * */
    fun onDoneClick(v: View) {
//        skipLiveData.value = v
        Log.e("LOG_TAG", "onDoneClick")
    }

    fun setupGameEngine(gameEngine: GameEngine) {

        if (gameEngine.currentPlayingTeam == null && gameEngine.teams.isNotEmpty()) {
            gameEngine.currentPlayingTeam = gameEngine.teams[0]
            gameEngine.round = 1
        }

        gameEngine.currentPlayingTeam?.let {
            it.roundScores[gameEngine.round] = 0
        }
        gameEngineLiveData.value = gameEngine

        totalScoreLiveData.value = gameEngine.currentPlayingTeam?.totalScore

        gameEngine.settings?.let {

            remainingRoundTimeLiveData.value = it.roundTime

            timer = object : CountDownTimer(it.roundTime * 1000L, 1000) {

                override fun onTick(millisUntilFinished: Long) {
                    remainingRoundTimeLiveData.value = (millisUntilFinished.toInt() / 1000 + 1)
                }

                override fun onFinish() {
                    finishRound()
                }
            }

            timer?.start()
        }

    }

    fun finishRound() {
        roundFinishedLiveData.value = gameEngineLiveData.value
        timer?.cancel()
        timer = null
    }

    private fun increaseDecreaseRoundScore(isIncrease: Boolean) {

        gameEngineLiveData.value?.let {

            var roundScore = it.currentPlayingTeam?.roundScores?.get(it.round) ?: 0
            if (isIncrease) {
                ++roundScore
            } else {
                --roundScore
            }

            it.currentPlayingTeam?.roundScores?.set(it.round, roundScore)
            roundScoreLiveData.value = roundScore

            var totalScore = it.currentPlayingTeam?.totalScore ?: 0
            if (isIncrease) {
                ++totalScore
            } else {
                --totalScore
            }

            it.currentPlayingTeam?.totalScore = totalScore
            totalScoreLiveData.value = totalScore

        }
    }

    fun handleWordGuessed(word: Word) {

        if (word.isGuessed) {
            gameEngineLiveData.value?.currentPlayingTeam?.words?.remove(word)
        } else {
            gameEngineLiveData.value?.currentPlayingTeam?.words?.add(word)
        }

        increaseDecreaseRoundScore(word.isGuessed)
    }

    fun getGameType(): GameType? {
        return gameEngineLiveData.value?.gameType
    }

    fun getGameWordLanguage(): String {
        return gameEngineLiveData.value?.settings?.gameWordLanguage ?: ""
    }

    fun getWordTranslateLanguage(): String {
        return gameEngineLiveData.value?.settings?.wordTranslateLanguage ?: ""
    }

    fun isWordTranslateEnabled(): Boolean {
        return gameEngineLiveData.value?.settings?.isWordTranslateEnabled ?: false
    }

    fun wordGuessedStateChanged(isGuested: Boolean) {
        if (isGuested) {
            ++selectedCount
        } else {
            --selectedCount
        }

        val itemCount = if (gameEngineLiveData.value?.gameType == GameType.Classic) 5 else 1

        if (selectedCount == itemCount) {
            generateRandomWordsList()
        }
    }

    fun generateRandomWordsList() {
        selectedCount = 0
        words = generateRandomWords()
        gameEngineLiveData.value = gameEngineLiveData.value
    }

    private fun generateRandomWords(): MutableList<Word> {

        val randomWords = mutableListOf<Word>()
        val wordsCount = if (getGameType() == GameType.Classic) 5 else 1

        gameEngineLiveData.value?.let { gameEngine ->

            gameEngine.currentPlayingTeam?.let { currentPlayingTeam ->
                if (wordsCount > currentPlayingTeam.words.size) {
                    for (word in gameEngine.allAvailableWords) {
                        gameEngine.currentPlayingTeam?.words?.add(word.copy())
                    }
                }
            }

            for (i in 0 until wordsCount) {
                gameEngine.currentPlayingTeam?.words?.let { words ->
                    while (randomWords.size != wordsCount) {
                        val word: Word = words.random()
                        if (!randomWords.contains(word)) {
                            randomWords.add(word)
                        }
                    }
                }
            }
        }
        return randomWords
    }
}