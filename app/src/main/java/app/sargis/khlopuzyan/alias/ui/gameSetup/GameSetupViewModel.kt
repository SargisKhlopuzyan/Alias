package app.sargis.khlopuzyan.alias.ui.gameSetup

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.sargis.khlopuzyan.alias.helper.SingleLiveEvent
import app.sargis.khlopuzyan.alias.model.Game
import app.sargis.khlopuzyan.alias.model.Settings
import app.sargis.khlopuzyan.alias.repository.GameSetupRepository

class GameSetupViewModel constructor(private val gameSetupRepository: GameSetupRepository) :
    ViewModel() {

    val startClassGameLiveData: SingleLiveEvent<Settings> = SingleLiveEvent()
    val startArcadeGameLiveData: SingleLiveEvent<Settings> = SingleLiveEvent()

    val game = MutableLiveData<Game>(Game())
    val settings = MutableLiveData<Settings>()

    /**
     * Handles Settings icon click
     * */
    fun onClassicClick(v: View) {
        startClassGameLiveData.value = settings.value
    }

    /**
     * Handles New Game icon click
     * */
    fun onArcadeClick(v: View) {
        startArcadeGameLiveData.value = settings.value
    }

}