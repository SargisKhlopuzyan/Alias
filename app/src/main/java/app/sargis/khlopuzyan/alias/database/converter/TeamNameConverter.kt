package app.sargis.khlopuzyan.alias.database.converter

import androidx.room.TypeConverter
import app.sargis.khlopuzyan.alias.model.TeamName
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by Sargis Khlopuzyan, on 2/28/2020.
 *
 * @author Sargis Khlopuzyan (sargis.khlopuzyan@fcc.am)
 */
class TeamNameConverter {

    private val gson = Gson()

    @TypeConverter
    fun stringToTeamName(data: String?): TeamName {

        if (data == null) {
            return TeamName()
        }

        val type = object : TypeToken<TeamName>() {}.type

        return gson.fromJson<TeamName>(data, type)
    }

    @TypeConverter
    fun teamNameToString(teamName: TeamName): String {
        return gson.toJson(teamName)
    }
}