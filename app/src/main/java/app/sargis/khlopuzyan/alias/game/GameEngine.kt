package app.sargis.khlopuzyan.alias.game

import android.os.Parcelable
import app.sargis.khlopuzyan.alias.model.GameType
import app.sargis.khlopuzyan.alias.model.Settings
import app.sargis.khlopuzyan.alias.model.Team
import app.sargis.khlopuzyan.alias.model.Word
import kotlinx.android.parcel.Parcelize

/**
 * Created by Sargis Khlopuzyan, on 2/27/2020.
 *
 * @author Sargis Khlopuzyan (sargis.khlopuzyan@fcc.am)
 */
@Parcelize
data class GameEngine(

    var round: Int = 1,
    var currentPlayingTeam: Team? = null,
    var gameType: GameType? = null,
    var allAvailableWords: MutableList<Word> = mutableListOf(),

    var teams: MutableList<Team> = mutableListOf(),
    var availableTeams: MutableList<Team> = mutableListOf(),

    var settings: Settings? = null

) : Parcelable