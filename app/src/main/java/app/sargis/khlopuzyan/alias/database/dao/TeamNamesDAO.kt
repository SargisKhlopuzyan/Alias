package app.sargis.khlopuzyan.alias.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import app.sargis.khlopuzyan.alias.model.Team

/**
 * Created by Sargis Khlopuzyan, on 2/28/2020.
 *
 * @author Sargis Khlopuzyan (sargis.khlopuzyan@fcc.am)
 */
@Dao
interface TeamNamesDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTeamName(team: Team): Long

    @Update
    fun updateTeamName(vararg teams: Team): Int

    @Query("DELETE FROM teams  WHERE uuid = :id")
    fun deleteTeamName(id: String?): Int

    @Query("SELECT * FROM teams WHERE name = :name")
    fun getTeamNameByName(name: String?): Team?

    @Query("SELECT * FROM teams WHERE name LIKE :name")
    fun getAllMatchedTeamNames(name: String?): List<Team>

    @Query("SELECT * FROM teams")
    fun getAllTeamNames(): List<Team>

    @Query("SELECT * FROM teams")
    fun getAllTeamNamesLiveData(): LiveData<List<Team>?>

}