package app.sargis.khlopuzyan.alias.database.converter

import androidx.room.TypeConverter
import app.sargis.khlopuzyan.alias.model.Team
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
    fun stringToTeamName(data: String?): Team {

        if (data == null) {
            return Team()
        }

        val type = object : TypeToken<Team>() {}.type

        return gson.fromJson<Team>(data, type)
    }

    @TypeConverter
    fun teamNameToString(team: Team): String {
        return gson.toJson(team)
    }
}