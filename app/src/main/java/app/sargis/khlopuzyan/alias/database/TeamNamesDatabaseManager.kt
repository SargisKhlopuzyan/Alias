package app.sargis.khlopuzyan.alias.database

import android.content.Context
import androidx.lifecycle.LiveData
import app.sargis.khlopuzyan.alias.model.Team
import javax.inject.Inject

/**
 * Created by Sargis Khlopuzyan, on 2/28/2020.
 *
 * @author Sargis Khlopuzyan (sargis.khlopuzyan@fcc.am)
 */

class TeamNamesDatabaseManager @Inject constructor(var context: Context) {

    fun saveTeamNameInDatabase(team: Team): Long {
        return AliasDb.getInstance(context).getTeamNamesDAO().insertTeamName(team)
    }

    fun deleteTeamNameFromDatabase(team: Team): Int {
        return AliasDb.getInstance(context).getTeamNamesDAO().deleteTeamName(team.uuid)
    }

    fun getTeamNameFromDatabase(nameEn: String): Team? {
        return AliasDb.getInstance(context).getTeamNamesDAO().getTeamNameByName(nameEn)
    }

    fun getAllMatchedTeamNamesFromDatabase(nameEn: String): List<Team> {
        return AliasDb.getInstance(context).getTeamNamesDAO().getAllMatchedTeamNames(nameEn)
    }

    fun getAllTeamNamesFromDatabase(): List<Team> {
        return AliasDb.getInstance(context).getTeamNamesDAO().getAllTeamNames()
    }

    fun getAllTeamNamesLiveDataFromDatabase(): LiveData<List<Team>?> {
        return AliasDb.getInstance(context).getTeamNamesDAO().getAllTeamNamesLiveData()
    }

}