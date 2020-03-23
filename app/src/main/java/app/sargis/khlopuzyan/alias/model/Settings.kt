package app.sargis.khlopuzyan.alias.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Sargis Khlopuzyan, on 2/27/2020.
 *
 * @author Sargis Khlopuzyan (sargis.khlopuzyan@fcc.am)
 */
@Parcelize
data class Settings(
    var numberOfWords: Int = 60,
    var roundTime: Int = 60,
    var teamsCount: Int = 2,
    var isGameSoundEnabled: Boolean = true,
    var isMissedWordPenaltyEnabled: Boolean = true,
    var appLanguage: String = "en",
    var gameWordLanguage: String = "en",
    var isWordTranslateEnabled: Boolean = true,
    var wordTranslateLanguage: String = "en"
) : Parcelable