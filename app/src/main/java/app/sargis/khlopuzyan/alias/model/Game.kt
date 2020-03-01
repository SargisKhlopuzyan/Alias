package app.sargis.khlopuzyan.alias.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Sargis Khlopuzyan, on 2/27/2020.
 *
 * @author Sargis Khlopuzyan (sargis.khlopuzyan@fcc.am)
 */
@Parcelize
data class Game(

    var round: Int = 1,
    var currentTeamName: String = "",

    var totalScore: Int = 0,
    var roundScore: Int = 0,
    var roundTimeRemaining: Int = 0,
    var gameType: GameType = GameType.Classic,

    val teamNames: List<TeamName> = listOf(),
    var settings: Settings = Settings()

) : Parcelable