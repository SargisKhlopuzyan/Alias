package app.sargis.khlopuzyan.alias.model

/**
 * Created by Sargis Khlopuzyan, on 2/27/2020.
 *
 * @author Sargis Khlopuzyan (sargis.khlopuzyan@fcc.am)
 */
data class Settings(
    var roundTime: Int = 20,
    var defaultTeamCount: Int = 2,
    var isGameSoundEnabled: Boolean = true,
    var isMissedWordPenaltyEnabled: Boolean = true,
    var appLanguage: String = "en",
    var gameWordLanguage: String = "en",
    var isWordTranslateEnabled: Boolean = true,
    var wordTranslateLanguage: String = "en"
)