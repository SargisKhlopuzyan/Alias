package app.sargis.khlopuzyan.alias.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import app.sargis.khlopuzyan.alias.model.TeamName

/**
 * Created by Sargis Khlopuzyan, on 2/28/2020.
 *
 * @author Sargis Khlopuzyan (sargis.khlopuzyan@fcc.am)
 */
@Dao
interface TeamNamesDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTeamName(item: String): Long

    @Update
    fun updateTeamName(vararg teamNames: TeamName): Int

    @Query("DELETE FROM teamNames WHERE teamName = :name")
    fun deleteTeamName(name: String?): Int

    @Query("SELECT * FROM teamNames WHERE teamName = :name")
    fun getTeamNameByName(name: String?): TeamName?

    @Query("SELECT * FROM teamNames WHERE teamName LIKE :name")
    fun getAllMatchedTeamNames(name: String?): List<TeamName>

    @Query("SELECT * FROM teamNames")
    fun getAllTeamNames(): List<TeamName>

    @Query("SELECT * FROM teamNames")
    fun getAllTeamNamesLiveData(): LiveData<List<TeamName>?>

}