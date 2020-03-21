package app.sargis.khlopuzyan.alias.ui.winner

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.sargis.khlopuzyan.alias.gameEngine.GameEngine
import app.sargis.khlopuzyan.alias.helper.SingleLiveEvent
import app.sargis.khlopuzyan.alias.model.Team

class WinnerViewModel : ViewModel() {

    val startNewGameLiveData: SingleLiveEvent<View> = SingleLiveEvent()

    val teamsLiveData = MutableLiveData<List<Team>>()

    fun setupGameEngine(gameEngine: GameEngine?) {
        val teams = gameEngine?.teams?.sortedByDescending { it.totalScore }
        teamsLiveData.value = teams
    }

    /**
     * Handles Settings icon click
     * */
    fun onStartNewGameClick(v: View) {
        startNewGameLiveData.value = v
    }
}
