package app.sargis.khlopuzyan.alias.repository

import app.sargis.khlopuzyan.alias.database.TeamNamesDatabaseManager
import app.sargis.khlopuzyan.alias.database.WordsDatabaseManager
import app.sargis.khlopuzyan.alias.model.Settings
import app.sargis.khlopuzyan.alias.model.Team
import app.sargis.khlopuzyan.alias.model.Word
import app.sargis.khlopuzyan.alias.sharedPref.SharedPrefManager
import app.sargis.khlopuzyan.alias.utils.constant.SharedPref

/**
 * Created by Sargis Khlopuzyan, on 2/27/2020.
 *
 * @author Sargis Khlopuzyan (sargis.khlopuzyan@fcc.am)
 */
interface GameSetupRepository {

    fun storeNumberOfWords(settings: Settings)
    fun storeRoundTime(settings: Settings)
    fun storeDefaultTeamsCount(settings: Settings)
    fun storeGameSoundState(settings: Settings)
    fun storeMissedWordPenaltyState(settings: Settings)
    fun storeAppLanguage(settings: Settings)
    fun storeGameWordsLanguage(settings: Settings)
    fun storeWordTranslateLanguage(settings: Settings)
    fun storeWordTranslateState(settings: Settings)

    fun loadSettings(): Settings
    fun loadTeamNames(): List<Team>
    fun loadWords(): List<Word>
}

class GameSetupRepositoryImpl(
    private val sharedPrefManager: SharedPrefManager,
    private val teamNamesDatabaseManager: TeamNamesDatabaseManager,
    private val wordsDatabaseManager: WordsDatabaseManager
) : GameSetupRepository {

    override fun storeNumberOfWords(settings: Settings) {
        sharedPrefManager.storeIntInSharedPref(
            SharedPref.SHARED_PREF_NUMBER_OF_WORDS,
            settings.numberOfWords
        )
    }

    override fun storeRoundTime(settings: Settings) {
        sharedPrefManager.storeIntInSharedPref(
            SharedPref.SHARED_PREF_ROUND_TIME,
            settings.roundTime
        )
    }

    override fun storeDefaultTeamsCount(settings: Settings) {
        sharedPrefManager.storeIntInSharedPref(
            SharedPref.SHARED_PREF_DEFAULT_TEAM_COUNT,
            settings.teamsCount
        )
    }

    override fun storeGameSoundState(settings: Settings) {
        sharedPrefManager.storeBooleanInSharedPref(
            SharedPref.SHARED_PREF_IS_GAME_SOUND_ENABLED,
            settings.isGameSoundEnabled
        )
    }


    override fun storeMissedWordPenaltyState(settings: Settings) {
        sharedPrefManager.storeBooleanInSharedPref(
            SharedPref.SHARED_PREF_MISSED_WORD_PENALTY,
            settings.isMissedWordPenaltyEnabled
        )
    }

    override fun storeAppLanguage(settings: Settings) {
        sharedPrefManager.storeStringInSharedPref(
            SharedPref.SHARED_PREF_APP_LANGUAGE,
            settings.appLanguage
        )
    }

    override fun storeGameWordsLanguage(settings: Settings) {
        sharedPrefManager.storeStringInSharedPref(
            SharedPref.SHARED_PREF_GAME_WORD_LANGUAGE,
            settings.gameWordLanguage
        )
    }

    override fun storeWordTranslateLanguage(settings: Settings) {
        sharedPrefManager.storeStringInSharedPref(
            SharedPref.SHARED_PREF_WORD_TRANSLATE_LANGUAGE,
            settings.wordTranslateLanguage
        )
    }

    override fun storeWordTranslateState(settings: Settings) {
        sharedPrefManager.storeBooleanInSharedPref(
            SharedPref.SHARED_PREF_IS_WORD_TRANSLATE_ENABLED,
            settings.isWordTranslateEnabled
        )
    }

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
                settings.teamsCount = 2
            else
                settings.teamsCount = it
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

    override fun loadTeamNames(): List<Team> {
        return teamNamesDatabaseManager.getAllTeamNamesFromDatabase()

    }

    override fun loadWords(): List<Word> {
        return wordsDatabaseManager.getAllWordsFromDatabase()
    }

}