package app.sargis.khlopuzyan.alias.ui.main

import android.util.Log
import android.view.View
import android.widget.CompoundButton
import android.widget.RadioGroup
import android.widget.SeekBar
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.sargis.khlopuzyan.alias.R
import app.sargis.khlopuzyan.alias.helper.SingleLiveEvent
import app.sargis.khlopuzyan.alias.model.Settings
import app.sargis.khlopuzyan.alias.repository.SettingsRepository

class MainViewModel constructor(private val settingsRepository: SettingsRepository) : ViewModel() {

    val openSettingsLiveData: SingleLiveEvent<View> = SingleLiveEvent()
    val newGameLiveData: SingleLiveEvent<View> = SingleLiveEvent()

    val settings = MutableLiveData<Settings>()

    init {
        settings.value = settingsRepository.loadSettings()
        Log.e("LOG_TAG", "settings: $settings")
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
    fun onRoundTimeProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
        if (fromUser) {
            settings.value?.roundTime = progress
            settings.value = settings.value
        }
    }

    fun onDefaultTeamsCountProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
        if (fromUser) {
            settings.value?.defaultTeamCount = progress
            settings.value = settings.value
        }
    }


    /** Checkbox : Checked Change */
    fun onGameSoundCheckedChange(button: CompoundButton, check: Boolean) {
        Log.e("LOG_TAG", "onGameSoundCheckedChange: $check")
    }

    fun onMissedWordPenaltyCheckedChange(button: CompoundButton, check: Boolean) {
        Log.e("LOG_TAG", "onMissedWordPenaltyCheckedChange: $check")
    }

    fun onEnableTranslateCheckedChange(button: CompoundButton, check: Boolean) {
        Log.e("LOG_TAG", "onEnableTranslateCheckedChange: $check")
    }

    /** Radio Group : Check Changed */
    fun onAppLanguageCheckChanged(radioGroup: RadioGroup, id: Int) {
        when (id) {
            R.id.radioButtonAppLanguageEnglish -> {
                Log.e("LOG_TAG", "English: $id")
            }
            R.id.radioButtonAppLanguageArmenian -> {
                Log.e("LOG_TAG", "Armenian: $id")
            }
            R.id.radioButtonAppLanguageRussian -> {
                Log.e("LOG_TAG", "Russian: $id")
            }
        }
    }

    fun onGameWordsLanguageCheckChanged(radioGroup: RadioGroup, id: Int) {
        when (id) {
            R.id.radioButtonGameWordsLanguageEnglish -> {
                Log.e("LOG_TAG", "English: $id")
            }
            R.id.radioButtonGameWordsLanguageArmenian -> {
                Log.e("LOG_TAG", "Armenian: $id")
            }
            R.id.radioButtonGameWordsLanguageRussian -> {
                Log.e("LOG_TAG", "Russian: $id")
            }
        }
    }

    fun onTranslateLanguageCheckChanged(radioGroup: RadioGroup, id: Int) {
        when (id) {
            R.id.radioButtonTranslateLanguageEnglish -> {
                Log.e("LOG_TAG", "English: $id")
            }
            R.id.radioButtonTranslateLanguageArmenian -> {
                Log.e("LOG_TAG", "Armenian: $id")
            }
            R.id.radioButtonTranslateLanguageRussian -> {
                Log.e("LOG_TAG", "Russian: $id")
            }
        }
    }

    fun storeAppLanguage() {
//        settingsRepository.storeAppLanguage()
    }

    fun storeGameWordsLanguage() {

    }

    fun storeTranslateLanguage() {

    }

}
