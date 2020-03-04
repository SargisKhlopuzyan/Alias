package app.sargis.khlopuzyan.alias.repository

import app.sargis.khlopuzyan.alias.database.TeamNamesDatabaseManager
import app.sargis.khlopuzyan.alias.database.WordsDatabaseManager
import app.sargis.khlopuzyan.alias.model.Settings
import app.sargis.khlopuzyan.alias.model.Word
import app.sargis.khlopuzyan.alias.sharedPref.SharedPrefManager
import app.sargis.khlopuzyan.alias.utils.constant.SharedPref

/**
 * Created by Sargis Khlopuzyan, on 2/27/2020.
 *
 * @author Sargis Khlopuzyan (sargis.khlopuzyan@fcc.am)
 */
interface StartGameRepository {
    fun loadSettings(): Settings
    fun loadWords(): List<Word>
}

class StartGameRepositoryImpl(
    private val teamNamesDatabaseManager: TeamNamesDatabaseManager,
    private val wordsDatabaseManager: WordsDatabaseManager,
    private val sharedPrefManager: SharedPrefManager
) : StartGameRepository {

    override fun loadSettings(): Settings {

        val settings = Settings()

        sharedPrefManager.loadIntFromSharedPref(SharedPref.SHARED_PREF_NUMBER_OF_WORDS).let {
            if (it == 0)
                settings.numberOfWords = 60
            else
                settings.numberOfWords = it
        }

        sharedPrefManager.loadIntFromSharedPref(SharedPref.SHARED_PREF_ROUND_TIME).let {
            if (it == 0)
                settings.roundTime = 10
            else
                settings.roundTime = it
        }

        sharedPrefManager.loadIntFromSharedPref(SharedPref.SHARED_PREF_DEFAULT_TEAM_COUNT).let {
            if (it == 0)
                settings.defaultTeamsCount = 2
            else
                settings.defaultTeamsCount = it
        }

        settings.isGameSoundEnabled =
            sharedPrefManager.loadBooleanFromSharedPref(SharedPref.SHARED_PREF_IS_GAME_SOUND_ENABLED)

        settings.isMissedWordPenaltyEnabled =
            sharedPrefManager.loadBooleanFromSharedPref(SharedPref.SHARED_PREF_MISSED_WORD_PENALTY)

        sharedPrefManager.loadStringFromSharedPref(SharedPref.SHARED_PREF_APP_LANGUAGE).let {
            if (it.isEmpty())
                settings.appLanguage = "en"
            else
                settings.appLanguage = it
        }

        sharedPrefManager.loadStringFromSharedPref(SharedPref.SHARED_PREF_GAME_WORD_LANGUAGE).let {
            if (it.isEmpty())
                settings.gameWordLanguage = "en"
            else
                settings.gameWordLanguage = it
        }

        sharedPrefManager.loadStringFromSharedPref(SharedPref.SHARED_PREF_WORD_TRANSLATE_LANGUAGE)
            .let {
                if (it.isEmpty())
                    settings.wordTranslateLanguage = "en"
                else
                    settings.wordTranslateLanguage = it
            }

        settings.isWordTranslateEnabled =
            sharedPrefManager.loadBooleanFromSharedPref(SharedPref.SHARED_PREF_IS_WORD_TRANSLATE_ENABLED)

        return settings
    }

    override fun loadWords(): List<Word> {
        return wordsDatabaseManager.getAllWordsFromDatabase()
    }
}