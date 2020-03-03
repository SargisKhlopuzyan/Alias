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

    private val availableTeams: MutableList<Team> = teamsRepository.loadTeamNames().toMutableList()
    var teams = mutableListOf<Team>()

    init {
        when {
            availableTeams.size > 1 -> {
                teams.add(availableTeams[0])
                teams.add(availableTeams[1])
                availableTeams.removeAt(0)
                availableTeams.removeAt(0)
                2
            }
            availableTeams.isNotEmpty() -> {
                teams.add(availableTeams[0])
                availableTeams.removeAt(0)
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
        if (availableTeams.isNotEmpty()) {
            val newTeam = availableTeams[0]
            teams.add(newTeam)
            availableTeams.removeAt(0) // TODO

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
            val deletedTeam = teams.removeAt(position)
            availableTeams.add(deletedTeam) // TODO

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