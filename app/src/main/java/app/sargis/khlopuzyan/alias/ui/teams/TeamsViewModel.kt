package app.sargis.khlopuzyan.alias.ui.teams

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.sargis.khlopuzyan.alias.helper.SingleLiveEvent
import app.sargis.khlopuzyan.alias.model.Team
import app.sargis.khlopuzyan.alias.repository.TeamsRepository

class TeamsViewModel constructor(teamsRepository: TeamsRepository) : ViewModel() {

    val changeTeamLiveData: SingleLiveEvent<Team> = SingleLiveEvent()
    val teamsChangedLiveData: SingleLiveEvent<List<Team>> = SingleLiveEvent()

    val teamsLiveData = MutableLiveData<MutableList<Team>>(mutableListOf())

    private val allAvailableTeams: List<Team> = teamsRepository.loadTeamNames()
    var teams = mutableListOf<Team>()

    init {
        when {
            allAvailableTeams.size > 1 -> {
                teams.add(allAvailableTeams[0])
                teams.add(allAvailableTeams[1])
                2
            }
            allAvailableTeams.isNotEmpty() -> {
                teams.add(allAvailableTeams[0])
                1
            }
            else -> {
                0
            }
        }

        teamsLiveData.value = teams
    }

    /**
     * Handles Settings icon click
     * */
    fun onAddTeamClick(v: View) {
        if (teams.size < allAvailableTeams.size) {

            val newTeam = allAvailableTeams[teams.size]
            teams.add(newTeam)
            teamsLiveData.value = teams

            teamsChangedLiveData.value = teams
        }
    }

    /**
     * Handles New Game icon click
     * */
    fun onDeleteTeamClick(team: Team) {
        if (teams.contains(team)) {
            val position = teams.indexOf(team)
            teams.removeAt(position)
            teamsLiveData.value = teams

            teamsChangedLiveData.value = teams
        }
    }

    /**
     * Handles New Game icon click
     * */
    fun onChangeTeamNameClick(team: Team) {
        changeTeamLiveData.value = team
    }

    fun changeTeamName(team: Team, newTeamName: String) {
        if (teams.contains(team)) {
            val position = teams.indexOf(team)
            teams[position].name = newTeamName
            teamsLiveData.value = teams

            teamsChangedLiveData.value = teams
        }
    }

}