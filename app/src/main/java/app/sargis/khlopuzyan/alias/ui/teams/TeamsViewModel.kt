package app.sargis.khlopuzyan.alias.ui.teams

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.sargis.khlopuzyan.alias.helper.SingleLiveEvent
import app.sargis.khlopuzyan.alias.model.Settings
import app.sargis.khlopuzyan.alias.model.TeamName
import app.sargis.khlopuzyan.alias.repository.TeamsRepository

class TeamsViewModel constructor(private val teamsRepository: TeamsRepository) : ViewModel() {

    val openSettingsLiveData: SingleLiveEvent<View> = SingleLiveEvent()
    val newGameLiveData: SingleLiveEvent<View> = SingleLiveEvent()

    val settings = MutableLiveData<Settings>()
    val teamNames: List<TeamName>

    val addNewTeamLiveData = MutableLiveData<TeamName>()

    init {
        settings.value = teamsRepository.loadSettings()

        teamNames = teamsRepository.loadTeamNames().toMutableList()
    }

    var teamsCount = 0
    /**
     * Handles Settings icon click
     * */
    fun onAddTeamClick(v: View) {
        if (teamsCount < teamNames.size - 1)
            addNewTeamLiveData.value = teamNames[++teamsCount]
    }

    /**
     * Handles New Game icon click
     * */
    fun onNewGameClick(v: View) {
        newGameLiveData.value = v
    }

}