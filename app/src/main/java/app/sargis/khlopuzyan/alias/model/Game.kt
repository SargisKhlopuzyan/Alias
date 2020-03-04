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
    var currentPlayingTeam: Team? = null,
    var gameType: GameType = GameType.Classic,

    var teams: List<Team> = listOf(),
    var settings: Settings = Settings()

) : Parcelable