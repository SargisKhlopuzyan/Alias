package app.sargis.khlopuzyan.alias.ui.teams

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.sargis.khlopuzyan.alias.helper.SingleLiveEvent
import app.sargis.khlopuzyan.alias.model.Team
import app.sargis.khlopuzyan.alias.repository.TeamsRepository

class TeamsViewModel constructor(teamsRepository: TeamsRepository) : ViewModel() {

    val changeTeamCLiveData: SingleLiveEvent<Team> = SingleLiveEvent()

    lateinit var gameTeamsChangeListener: TeamsFragment.GameTeamsChangeListener

    val addNewTeamLiveData = MutableLiveData<Team>()

    private val teams: List<Team>


    init {
        teams = teamsRepository.loadTeamNames().toMutableList()
    }

    var teamsCount = 0
    /**
     * Handles Settings icon click
     * */
    fun onAddTeamClick(v: View) {
        if (teamsCount < teams.size - 1) {
            val newTeamName = teams[++teamsCount]
            gameTeamsChangeListener.addTeamName(newTeamName)
            addNewTeamLiveData.value = newTeamName
        }
    }

    /**
     * Handles New Game icon click
     * */
    fun onRemoveTeamClick(team: Team) {
        gameTeamsChangeListener.removeTeam(team)
    }

    /**
     * Handles New Game icon click
     * */
    fun onDeleteTeamClick(team: Team) {
        Log.e("LOG_TAG", "onDeleteTeamClick")
    }

    /**
     * Handles New Game icon click
     * */
    fun onChangeTeamNameClick(team: Team) {
        changeTeamCLiveData.value = team
    }



}