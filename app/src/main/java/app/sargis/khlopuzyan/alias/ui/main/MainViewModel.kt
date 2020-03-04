package app.sargis.khlopuzyan.alias.ui.main

import android.view.View
import android.widget.CompoundButton
import android.widget.RadioGroup
import android.widget.SeekBar
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.sargis.khlopuzyan.alias.R
import app.sargis.khlopuzyan.alias.helper.SingleLiveEvent
import app.sargis.khlopuzyan.alias.model.Language
import app.sargis.khlopuzyan.alias.model.Settings
import app.sargis.khlopuzyan.alias.repository.SettingsRepository

class MainViewModel constructor(private val settingsRepository: SettingsRepository) : ViewModel() {

    val openSettingsLiveData: SingleLiveEvent<View> = SingleLiveEvent()
    val newGameLiveData: SingleLiveEvent<View> = SingleLiveEvent()

    val settings = MutableLiveData<Settings>()

    init {
        settings.value = settingsRepository.loadSettings()
    }

    //TODO
    fun storeDefaultTeamNames() {
        settingsRepository.storeDefaultParameters()
    }

    /**
     * Handles Settings icon click
     * */
    fun onOpenSettingsClick(v: View) {
        openSettingsLiveData.value = v
    }

    /**
     * Handles New Game icon click
     * */
    fun onNewGameClick(v: View) {
        newGameLiveData.value = v
    }


    /**SeekBar : Progress Changed */

    fun onNumberOfWordsProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
        if (fromUser) {
            settings.value?.numberOfWords = progress
            settings.value?.let {
                settingsRepository.storeNumberOfWords(it)
            }
            settings.value = settings.value
        }
    }

    fun onRoundTimeProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
        if (fromUser) {
            settings.value?.roundTime = progress
            settings.value?.let {
                settingsRepository.storeRoundTime(it)
            }
            settings.value = settings.value
        }
    }

    fun onDefaultTeamsCountProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
        if (fromUser) {
            settings.value?.defaultTeamsCount = progress
            settings.value?.let {
                settingsRepository.storeDefaultTeamsCount(it)
            }
            settings.value = settings.value
        }
    }


    /** Checkbox : Checked Change */
    fun onGameSoundCheckedChange(button: CompoundButton, check: Boolean) {
        settings.value?.isGameSoundEnabled = check
        settings.value?.let {
            settingsRepository.storeGameSoundState(it)
        }
    }

    fun onMissedWordPenaltyCheckedChange(button: CompoundButton, check: Boolean) {
        settings.value?.isMissedWordPenaltyEnabled = check
        settings.value?.let {
            settingsRepository.storeMissedWordPenaltyState(it)
        }
    }

    fun onEnableTranslateCheckedChange(button: CompoundButton, check: Boolean) {
        settings.value?.isWordTranslateEnabled = check
        settings.value?.let {
            settingsRepository.storeWordTranslateState(it)
        }
    }

    /** Radio Group : Check Changed */
    fun onAppLanguageCheckChanged(radioGroup: RadioGroup, id: Int) {
        when (id) {
            R.id.radioButtonAppLanguageEnglish -> {
                settings.value?.appLanguage = Language.EN
            }
            R.id.radioButtonAppLanguageArmenian -> {
                settings.value?.appLanguage = Language.AM
            }
            R.id.radioButtonAppLanguageRussian -> {
                settings.value?.appLanguage = Language.RU
            }
        }

        settings.value?.let {
            settingsRepository.storeAppLanguage(it)
        }
    }

    fun onGameWordsLanguageCheckChanged(radioGroup: RadioGroup, id: Int) {
        when (id) {
            R.id.radioButtonGameWordsLanguageEnglish -> {
                settings.value?.gameWordLanguage = Language.EN
            }
            R.id.radioButtonGameWordsLanguageArmenian -> {
                settings.value?.gameWordLanguage = Language.AM
            }
            R.id.radioButtonGameWordsLanguageRussian -> {
                settings.value?.gameWordLanguage = Language.RU
            }
        }

        settings.value?.let {
            settingsRepository.storeGameWordsLanguage(it)
        }
    }

    fun onTranslateLanguageCheckChanged(radioGroup: RadioGroup, id: Int) {
        when (id) {
            R.id.radioButtonWordTranslateLanguageEnglish -> {
                settings.value?.wordTranslateLanguage = Language.EN
            }
            R.id.radioButtonWordTranslateLanguageArmenian -> {
                settings.value?.wordTranslateLanguage = Language.AM
            }
            R.id.radioButtonWordTranslateLanguageRussian -> {
                settings.value?.wordTranslateLanguage = Language.RU
            }
        }

        settings.value?.let {
            settingsRepository.storeWordTranslateLanguage(it)
        }
    }

}
