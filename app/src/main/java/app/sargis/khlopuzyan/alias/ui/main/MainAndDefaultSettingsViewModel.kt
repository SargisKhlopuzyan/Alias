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
import app.sargis.khlopuzyan.alias.repository.DefaultSettingsRepository

class MainAndDefaultSettingsViewModel constructor(private val defaultSettingsRepository: DefaultSettingsRepository) :
    ViewModel() {

    val openSettingsLiveData: SingleLiveEvent<View> = SingleLiveEvent()
    val newGameLiveData: SingleLiveEvent<View> = SingleLiveEvent()

    val settings = MutableLiveData<Settings>()

    init {
        settings.value = defaultSettingsRepository.loadSettings()
    }

    //TODO
    fun storeDefaultTeamNames() {
        defaultSettingsRepository.storeDefaultParameters()
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
                defaultSettingsRepository.storeNumberOfWords(it)
            }
            settings.value = settings.value
        }
    }

    fun onRoundTimeProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
        if (fromUser) {
            settings.value?.roundTime = progress
            settings.value?.let {
                defaultSettingsRepository.storeRoundTime(it)
            }
            settings.value = settings.value
        }
    }

    fun onTeamsCountProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
        if (fromUser) {
            settings.value?.let {
                it.teamsCount = progress
                defaultSettingsRepository.storeDefaultTeamsCount(it)
            }
            settings.value = settings.value
        }
    }

    /** Checkbox : Checked Change */
    fun onGameSoundCheckedChange(button: CompoundButton, check: Boolean) {
        settings.value?.let {
            it.isGameSoundEnabled = check
            defaultSettingsRepository.storeGameSoundState(it)
        }
    }

    fun onMissedWordPenaltyCheckedChange(button: CompoundButton, check: Boolean) {
        settings.value?.let {
            it.isMissedWordPenaltyEnabled = check
            defaultSettingsRepository.storeMissedWordPenaltyState(it)
        }
    }

    fun onEnableTranslateCheckedChange(button: CompoundButton, check: Boolean) {
        settings.value?.let {
            it.isWordTranslateEnabled = check
            defaultSettingsRepository.storeWordTranslateState(it)
        }
        settings.value = settings.value
    }

    /** Radio Group : Check Changed */
    fun onAppLanguageCheckChanged(radioGroup: RadioGroup, id: Int) {
        when (id) {
            R.id.radioButtonAppLanguageEn -> {
                settings.value?.appLanguage = Language.EN
            }
            R.id.radioButtonAppLanguageAm -> {
                settings.value?.appLanguage = Language.AM
            }
            R.id.radioButtonAppLanguageRu -> {
                settings.value?.appLanguage = Language.RU
            }
        }

        settings.value?.let {
            defaultSettingsRepository.storeAppLanguage(it)
        }
    }

    fun onGameWordsLanguageCheckChanged(radioGroup: RadioGroup, id: Int) {
        when (id) {
            R.id.radioButtonGameWordsLanguageEn -> {
                settings.value?.gameWordLanguage = Language.EN
            }
            R.id.radioButtonGameWordsLanguageAm -> {
                settings.value?.gameWordLanguage = Language.AM
            }
            R.id.radioButtonGameWordsLanguageRu -> {
                settings.value?.gameWordLanguage = Language.RU
            }
        }

        settings.value?.let {
            defaultSettingsRepository.storeGameWordsLanguage(it)
        }

        settings.value = settings.value
    }

    fun onTranslateLanguageCheckChanged(radioGroup: RadioGroup, id: Int) {
        when (id) {
            R.id.radioButtonWordTranslateLanguageEn -> {
                settings.value?.wordTranslateLanguage = Language.EN
            }
            R.id.radioButtonWordTranslateLanguageAm -> {
                settings.value?.wordTranslateLanguage = Language.AM
            }
            R.id.radioButtonWordTranslateLanguageRu -> {
                settings.value?.wordTranslateLanguage = Language.RU
            }
        }

        settings.value?.let {
            defaultSettingsRepository.storeWordTranslateLanguage(it)
        }
    }

}
