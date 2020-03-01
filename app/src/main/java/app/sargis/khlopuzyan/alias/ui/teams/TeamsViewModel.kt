package app.sargis.khlopuzyan.alias.ui.teams

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.sargis.khlopuzyan.alias.helper.SingleLiveEvent
import app.sargis.khlopuzyan.alias.model.TeamName
import app.sargis.khlopuzyan.alias.repository.TeamsRepository

class TeamsViewModel constructor(teamsRepository: TeamsRepository) : ViewModel() {

    val changeTeamNameCLiveData: SingleLiveEvent<TeamName> = SingleLiveEvent()

    lateinit var gameTeamsChangeListener: TeamsFragment.GameTeamsChangeListener


    val addNewTeamLiveData = MutableLiveData<TeamName>()

    private val teamNames: List<TeamName>


    init {
        teamNames = teamsRepository.loadTeamNames().toMutableList()
    }

    var teamsCount = 0
    /**
     * Handles Settings icon click
     * */
    fun onAddTeamClick(v: View) {
        if (teamsCount < teamNames.size - 1) {
            val newTeamName = teamNames[++teamsCount]
            gameTeamsChangeListener.addTeamName(newTeamName)
            addNewTeamLiveData.value = newTeamName
        }
    }

    /**
     * Handles New Game icon click
     * */
    fun onRemoveTeamClick(teamName: TeamName) {

    }

    /**
     * Handles New Game icon click
     * */
    fun onChangeTeamNameClick(oldTeamName: TeamName, newTeamName: TeamName) {
        changeTeamNameCLiveData.value = newTeamName
    }

}