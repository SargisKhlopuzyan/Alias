package app.sargis.khlopuzyan.alias.helper

import android.widget.RadioGroup
import androidx.core.view.get
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import app.sargis.khlopuzyan.alias.R
import app.sargis.khlopuzyan.alias.model.Language
import app.sargis.khlopuzyan.alias.model.Settings
import app.sargis.khlopuzyan.alias.ui.common.BindableAdapter

/**
 * Created by Sargis Khlopuzyan, on 2/28/2020.
 *
 * @author Sargis Khlopuzyan (sargis.khlopuzyan@gmail.com)
 */

@BindingAdapter("data")
fun <T> RecyclerView.setRecyclerViewData(data: T?) {
    if (adapter is BindableAdapter<*>) {
        @Suppress("UNCHECKED_CAST")
        (adapter as BindableAdapter<T>).setItem(data)
        adapter?.let {
            scrollToPosition(it.itemCount - 1)
        }
    }
}

@BindingAdapter("bindAppLanguageCheckedButton")
fun RadioGroup.bindAppLanguageCheckedButton(settings: Settings) {
    when (settings.appLanguage) {
        Language.EN -> this.check(R.id.radioButtonAppLanguageEn)
        Language.AM -> this.check(R.id.radioButtonAppLanguageAm)
        Language.RU -> this.check(R.id.radioButtonAppLanguageRu)
    }

}

@BindingAdapter("bindGameWordsLanguageCheckedButton")
fun RadioGroup.bindGameWordsLanguageCheckedButton(settings: Settings) {
    when (settings.gameWordLanguage) {
        Language.EN -> this.check(R.id.radioButtonGameWordsLanguageEn)
        Language.AM -> this.check(R.id.radioButtonGameWordsLanguageAm)
        Language.RU -> this.check(R.id.radioButtonGameWordsLanguageRu)
    }

}

@BindingAdapter("bindWordTranslateLanguageCheckedButton")
fun RadioGroup.bindWordTranslateLanguageCheckedButton(settings: Settings) {

    when (settings.gameWordLanguage) {
        Language.EN -> {
            this[0].isEnabled = false
            this[1].isEnabled = true
            this[2].isEnabled = true
        }
        Language.AM -> {
            this[0].isEnabled = true
            this[1].isEnabled = false
            this[2].isEnabled = true
        }
        Language.RU -> {
            this[0].isEnabled = true
            this[1].isEnabled = true
            this[2].isEnabled = false
        }
    }

    when (settings.wordTranslateLanguage) {
        Language.EN -> {
            if (settings.gameWordLanguage.equals(Language.EN, true)) {
                settings.wordTranslateLanguage = Language.AM
                this.check(R.id.radioButtonWordTranslateLanguageAm)
            } else {
                this.check(R.id.radioButtonWordTranslateLanguageEn)
            }
        }
        Language.AM -> {
            if (settings.gameWordLanguage.equals(Language.AM, true)) {
                settings.wordTranslateLanguage = Language.RU
                this.check(R.id.radioButtonWordTranslateLanguageRu)
            } else {
                this.check(R.id.radioButtonWordTranslateLanguageAm)
            }
        }
        Language.RU -> {
            if (settings.gameWordLanguage.equals(Language.RU, true)) {
                settings.wordTranslateLanguage = Language.EN
                this.check(R.id.radioButtonWordTranslateLanguageEn)
            } else {
                this.check(R.id.radioButtonWordTranslateLanguageRu)
            }
        }
    }

    if (!settings.isWordTranslateEnabled) {
        this[0].isEnabled = false
        this[1].isEnabled = false
        this[2].isEnabled = false
    }
}