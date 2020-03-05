package app.sargis.khlopuzyan.alias.ui.game

import android.os.CountDownTimer
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.sargis.khlopuzyan.alias.helper.SingleLiveEvent
import app.sargis.khlopuzyan.alias.model.Game

class GameViewModel : ViewModel() {

    val closeLiveData: SingleLiveEvent<View> = SingleLiveEvent()
    val skipLiveData: SingleLiveEvent<View> = SingleLiveEvent()

    val gameLiveData = MutableLiveData<Game>(Game())

    val roundFinishedLiveData = MutableLiveData<Game>()
    val remainingRoundTimeLiveData = MutableLiveData(60)
    val roundScoreLiveData = MutableLiveData(0)
    val totalScoreLiveData = MutableLiveData(0)

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
        skipLiveData.value = v
        increaseDecreaseRoundScore(false)
    }

    /**
     * Handles New Game icon click
     * */
    fun onDoneClick(v: View) {
        skipLiveData.value = v
    }

    fun setGame(game: Game) {

        if (game.currentPlayingTeam == null && game.teams.isNotEmpty()) {
            game.currentPlayingTeam = game.teams[0]
            game.round = 1
        }

        gameLiveData.value = game

        remainingRoundTimeLiveData.value = game.settings.roundTime
        totalScoreLiveData.value = game.currentPlayingTeam?.totalScore

        timer = object : CountDownTimer(game.settings.roundTime * 1000L, 1000) {
//        timer = object : CountDownTimer(3 * 1000L, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                Log.e("LOG_TAG", "onTick")
                remainingRoundTimeLiveData.value = (millisUntilFinished.toInt() / 1000 + 1)
            }

            override fun onFinish() {
                val roundScore = roundScoreLiveData.value ?: 0
                gameLiveData.value?.currentPlayingTeam?.roundScore = roundScore

                var totalScore = gameLiveData.value?.currentPlayingTeam?.totalScore ?: 0
                totalScore += roundScore
                gameLiveData.value?.currentPlayingTeam?.totalScore = totalScore
                roundFinishedLiveData.value = gameLiveData.value
                timer = null
                Log.e("LOG_TAG", "onFinish")
            }
        }

        timer?.start()
    }

    private fun increaseDecreaseRoundScore(isIncrease: Boolean) {
        var roundScore = roundScoreLiveData.value ?: 0

        if (isIncrease) {
            ++roundScore
        } else {
            --roundScore
        }

        gameLiveData.value?.currentPlayingTeam?.roundScore = roundScore
        roundScoreLiveData.value = roundScore

        var totalScore = totalScoreLiveData.value ?: 0
        --totalScore
        gameLiveData.value?.currentPlayingTeam?.totalScore = totalScore
        totalScoreLiveData.value = totalScore
    }

}