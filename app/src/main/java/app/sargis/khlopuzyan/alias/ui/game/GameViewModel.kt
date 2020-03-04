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

    val remainingRoundTimeLiveData = MutableLiveData(60)
    val roundScoreLiveData = MutableLiveData(0)
    val totalScoreLiveData = MutableLiveData(0)

    private lateinit var timer: CountDownTimer

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
        decreaseRoundScore()
    }

    /**
     * Handles New Game icon click
     * */
    fun onDoneClick(v: View) {
        skipLiveData.value = v
    }

    fun setGame(game: Game) {
        if (game.teams.isNotEmpty()) {
            game.currentPlayingTeam = game.teams[0]
        }
        game.round = 1
        gameLiveData.value = game

        remainingRoundTimeLiveData.value = game.settings.roundTime
        totalScoreLiveData.value = game.currentPlayingTeam?.totalScore

        timer = object : CountDownTimer(game.settings.roundTime * 1000L, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                Log.e("LOG_TAG", "onTick")
                remainingRoundTimeLiveData.value = (millisUntilFinished.toInt() / 1000 + 1)
            }

            override fun onFinish() {
                Log.e("LOG_TAG", "onFinish")
            }
        }

        timer.start()
    }

    fun increaseRoundScore() {
        var roundTotalScore = roundScoreLiveData.value ?: 0
        ++roundTotalScore
        roundScoreLiveData.value = roundTotalScore

        var totalTotalScore = totalScoreLiveData.value ?: 0
        ++totalTotalScore
        roundScoreLiveData.value = totalTotalScore
    }

    fun decreaseRoundScore() {
        var roundTotalScore = roundScoreLiveData.value ?: 0
        --roundTotalScore

//        gameLiveData.value?.currentPlayingTeam?.roundScore?.let {
//            gameLiveData.value?.currentPlayingTeam?.roundScore = it - 1
//        }

        roundScoreLiveData.value = roundTotalScore

        var totalTotalScore = totalScoreLiveData.value ?: 0
        --totalTotalScore
        roundScoreLiveData.value = totalTotalScore
    }

}