package app.sargis.khlopuzyan.alias.repository

import app.sargis.khlopuzyan.alias.model.Settings
import app.sargis.khlopuzyan.alias.sharedPref.SharedPrefManager

/**
 * Created by Sargis Khlopuzyan, on 2/27/2020.
 *
 * @author Sargis Khlopuzyan (sargis.khlopuzyan@fcc.am)
 */
interface TeamsRepository {
    fun storeRoundTime(settings: Settings)
    fun storeDefaultTeamsCount(settings: Settings)
    fun storeGameSoundState(settings: Settings)
    fun storeMissedWordPenaltyState(settings: Settings)
    fun storeAppLanguage(settings: Settings)
    fun storeGameWordsLanguage(settings: Settings)
    fun storeWordTranslateLanguage(settings: Settings)
    fun storeWordTranslateState(settings: Settings)

    fun loadSettings(): Settings
}

class TeamsRepositoryImpl(
    private val sharedPrefManager: SharedPrefManager
) : TeamsRepository {
    override fun storeRoundTime(settings: Settings) {
        sharedPrefManager.storeIntInSharedPref(SHARED_PREF_ROUND_TIME, settings.roundTime)
    }

    override fun storeDefaultTeamsCount(settings: Settings) {
        sharedPrefManager.storeIntInSharedPref(
            SHARED_PREF_DEFAULT_TEAM_COUNT,
            settings.defaultTeamsCount
        )
    }

    override fun storeGameSoundState(settings: Settings) {
        sharedPrefManager.storeBooleanInSharedPref(
            SHARED_PREF_IS_GAME_SOUND_ENABLED,
            settings.isGameSoundEnabled
        )
    }


    override fun storeMissedWordPenaltyState(settings: Settings) {
        sharedPrefManager.storeBooleanInSharedPref(
            SHARED_PREF_MISSED_WORD_PENALTY,
            settings.isMissedWordPenaltyEnabled
        )
    }

    override fun storeAppLanguage(settings: Settings) {
        sharedPrefManager.storeStringInSharedPref(SHARED_PREF_APP_LANGUAGE, settings.appLanguage)
    }

    override fun storeGameWordsLanguage(settings: Settings) {
        sharedPrefManager.storeStringInSharedPref(
            SHARED_PREF_GAME_WORD_LANGUAGE,
            settings.gameWordLanguage
        )
    }

    override fun storeWordTranslateLanguage(settings: Settings) {
        sharedPrefManager.storeStringInSharedPref(
            SHARED_PREF_WORD_TRANSLATE_LANGUAGE,
            settings.wordTranslateLanguage
        )
    }

    override fun storeWordTranslateState(settings: Settings) {
        sharedPrefManager.storeBooleanInSharedPref(
            SHARED_PREF_IS_WORD_TRANSLATE_ENABLED,
            settings.isWordTranslateEnabled
        )
    }

    override fun loadSettings(): Settings {

        val settings = Settings()

        sharedPrefManager.loadIntFromSharedPref(SHARED_PREF_ROUND_TIME).let {
            if (it == 0)
                settings.roundTime = 10
            else
                settings.roundTime = it
        }

        sharedPrefManager.loadIntFromSharedPref(SHARED_PREF_DEFAULT_TEAM_COUNT).let {
            if (it == 0)
                settings.defaultTeamsCount = 2
            else
                settings.defaultTeamsCount = it
        }

        settings.isGameSoundEnabled =
            sharedPrefManager.loadBooleanFromSharedPref(SHARED_PREF_IS_GAME_SOUND_ENABLED)

        settings.isMissedWordPenaltyEnabled =
            sharedPrefManager.loadBooleanFromSharedPref(SHARED_PREF_MISSED_WORD_PENALTY)

        sharedPrefManager.loadStringFromSharedPref(SHARED_PREF_APP_LANGUAGE).let {
            if (it.isEmpty())
                settings.appLanguage = "en"
            else
                settings.appLanguage = it
        }

        sharedPrefManager.loadStringFromSharedPref(SHARED_PREF_GAME_WORD_LANGUAGE).let {
            if (it.isEmpty())
                settings.gameWordLanguage = "en"
            else
                settings.gameWordLanguage = it
        }

        sharedPrefManager.loadStringFromSharedPref(SHARED_PREF_WORD_TRANSLATE_LANGUAGE).let {
            if (it.isEmpty())
                settings.wordTranslateLanguage = "en"
            else
                settings.wordTranslateLanguage = it
        }

        settings.isWordTranslateEnabled =
            sharedPrefManager.loadBooleanFromSharedPref(SHARED_PREF_IS_WORD_TRANSLATE_ENABLED)

        return settings
    }

    companion object {
        const val SHARED_PREF_ROUND_TIME = "ROUND_TIME"
        const val SHARED_PREF_DEFAULT_TEAM_COUNT = "DEFAULT_TEAM_COUNT"
        const val SHARED_PREF_IS_GAME_SOUND_ENABLED = "GAME_SOUND"
        const val SHARED_PREF_MISSED_WORD_PENALTY = "MISSED_WORD_PENALTY"
        const val SHARED_PREF_APP_LANGUAGE = "APP_LANGUAGE"
        const val SHARED_PREF_GAME_WORD_LANGUAGE = "GAME_WORD_LANGUAGE"
        const val SHARED_PREF_WORD_TRANSLATE_LANGUAGE = "WORD_TRANSLATE_LANGUAGE"
        const val SHARED_PREF_IS_WORD_TRANSLATE_ENABLED = "IS_WORD_TRANSLATE_ENABLED"
    }
}