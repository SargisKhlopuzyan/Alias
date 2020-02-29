package app.sargis.khlopuzyan.alias.model

/**
 * Created by Sargis Khlopuzyan, on 2/27/2020.
 *
 * @author Sargis Khlopuzyan (sargis.khlopuzyan@fcc.am)
 */
data class Game(
    var round: Int = 1,
    var currentTeamName: String = "",

    var totalScore: Int = 0,
    var roundScore: Int = 0,
    var roundTimeRemaining: Int = 0
)