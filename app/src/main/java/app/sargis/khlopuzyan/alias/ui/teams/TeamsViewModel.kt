package app.sargis.khlopuzyan.alias.ui.teams

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.sargis.khlopuzyan.alias.gameEngine.GameEngine
import app.sargis.khlopuzyan.alias.helper.SingleLiveEvent
import app.sargis.khlopuzyan.alias.model.Team

class TeamsViewModel : ViewModel() {

    lateinit var gameEngine: GameEngine

    val changeTeamLiveData: SingleLiveEvent<Team> = SingleLiveEvent()

    val teamsLiveData = MutableLiveData<MutableList<Team>>(mutableListOf())

    fun setupGameEngine(gameEngine: GameEngine) {
        this.gameEngine = gameEngine

        teamsLiveData.value = gameEngine.teams
    }

    /**
     * Handles Settings icon click
     * */
    fun onAddTeamClick(v: View) {
        if (gameEngine.availableTeams.isNotEmpty()) {
            val team = gameEngine.availableTeams.removeAt(0)

            team.words.clear()
            for (word in gameEngine.allAvailableWords) {
                team.words.add(word.copy())
            }

            gameEngine.teams.add(team)
            teamsLiveData.value = gameEngine.teams
        }
    }

    /**
     * Handles New Game icon click
     * */
    fun onDeleteTeamClick(team: Team) {
        if (gameEngine.teams.contains(team)) {
            gameEngine.teams.remove(team)
            team.words.clear()
            gameEngine.availableTeams.add(team)
            teamsLiveData.value = gameEngine.teams
        }
    }

    /**
     * Handles New Game icon click
     * */
    fun onChangeTeamNameClick(team: Team) {
        changeTeamLiveData.value = team
    }

    fun changeTeamName(team: Team, newTeamName: String) {
        if (gameEngine.teams.contains(team)) {
            val position = gameEngine.teams.indexOf(team)
            gameEngine.teams[position].name = newTeamName
            teamsLiveData.value = gameEngine.teams
        }
    }
}