package app.sargis.khlopuzyan.alias.database

import android.content.Context
import androidx.lifecycle.LiveData
import app.sargis.khlopuzyan.alias.model.TeamName
import javax.inject.Inject

/**
 * Created by Sargis Khlopuzyan, on 2/28/2020.
 *
 * @author Sargis Khlopuzyan (sargis.khlopuzyan@fcc.am)
 */

class TeamNamesDatabaseManager @Inject constructor(var context: Context) {

    fun saveTeamNameInDatabase(teamName: TeamName): Long {
        return AliasDb.getInstance(context).getTeamNamesDAO().insertTeamName(teamName)
    }

    fun deleteTeamNameFromDatabase(teamName: TeamName): Int {
        return AliasDb.getInstance(context).getTeamNamesDAO().deleteTeamName(teamName.uuid)
    }

    fun getTeamNameFromDatabase(nameEn: String): TeamName? {
        return AliasDb.getInstance(context).getTeamNamesDAO().getTeamNameByName(nameEn)
    }

    fun getAllMatchedTeamNamesFromDatabase(nameEn: String): List<TeamName> {
        return AliasDb.getInstance(context).getTeamNamesDAO().getAllMatchedTeamNames(nameEn)
    }

    fun getAllTeamNamesFromDatabase(): List<TeamName> {
        return AliasDb.getInstance(context).getTeamNamesDAO().getAllTeamNames()
    }

    fun getAllTeamNamesLiveDataFromDatabase(): LiveData<List<TeamName>?> {
        return AliasDb.getInstance(context).getTeamNamesDAO().getAllTeamNamesLiveData()
    }

}