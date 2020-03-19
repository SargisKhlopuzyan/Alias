package app.sargis.khlopuzyan.alias.ui.gameSettings

import android.widget.CompoundButton
import android.widget.RadioGroup
import android.widget.SeekBar
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.sargis.khlopuzyan.alias.R
import app.sargis.khlopuzyan.alias.gameEngine.GameEngine
import app.sargis.khlopuzyan.alias.model.Language
import app.sargis.khlopuzyan.alias.model.Settings

class GameSettingsViewModel : ViewModel() {

    lateinit var gameEngine: GameEngine
    val settings = MutableLiveData<Settings>()

    lateinit var gameSettingsChangedListener: GameSettingsFragment.GameSettingsChangedListener

    fun setupGameEngine(gameEngine: GameEngine) {
        this.gameEngine = gameEngine
        settings.value = gameEngine.settings
    }

    /**SeekBar : Progress Changed */
    fun onNumberOfWordsProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
        if (fromUser) {
            settings.value?.numberOfWords = progress
            settings.value?.let {
                gameSettingsChangedListener.setNumberOfWords(progress)
            }
            settings.value = settings.value
        }
    }

    fun onRoundTimeProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
        if (fromUser) {
            settings.value?.roundTime = progress
            settings.value?.let {
                gameSettingsChangedListener.setRoundTime(progress)
            }
            settings.value = settings.value
        }
    }

    /** Checkbox : Checked Change */
    fun onGameSoundCheckedChange(button: CompoundButton, check: Boolean) {
        settings.value?.isGameSoundEnabled = check
        settings.value?.let {
            gameSettingsChangedListener.setGameSoundState(check)
        }
    }

    fun onMissedWordPenaltyCheckedChange(button: CompoundButton, check: Boolean) {
        settings.value?.isMissedWordPenaltyEnabled = check
        settings.value?.let {
            gameSettingsChangedListener.setMissedWordPenaltyState(check)
        }
    }

    fun onEnableTranslateCheckedChange(button: CompoundButton, check: Boolean) {
        settings.value?.isWordTranslateEnabled = check
        settings.value?.let {
            gameSettingsChangedListener.setTranslateEnabledState(check)
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
            gameSettingsChangedListener.setGameWordsLanguage(it.gameWordLanguage)
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
            gameSettingsChangedListener.setTranslateLanguage(it.wordTranslateLanguage)
        }
    }

}