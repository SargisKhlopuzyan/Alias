package app.sargis.khlopuzyan.alias.helper

import android.widget.RadioGroup
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
fun <T> RecyclerView.setRecyclerViewData(items: T?) {
    if (adapter is BindableAdapter<*>) {
        @Suppress("UNCHECKED_CAST")
        (adapter as BindableAdapter<T>).setItems(items)
        adapter?.let {
            scrollToPosition(it.itemCount - 1)
        }
    }
}

@BindingAdapter("bindAppLanguageCheckedButton")
fun RadioGroup.bindAppLanguageCheckedButton(settings: Settings) {
    when (settings.appLanguage) {
        Language.EN -> this.check(R.id.radioButtonAppLanguageEnglish)
        Language.AM -> this.check(R.id.radioButtonAppLanguageArmenian)
        Language.RU -> this.check(R.id.radioButtonAppLanguageRussian)
    }

}

@BindingAdapter("bindGameWordsLanguageCheckedButton")
fun RadioGroup.bindGameWordsLanguageCheckedButton(settings: Settings) {
    when (settings.gameWordLanguage) {
        Language.EN -> this.check(R.id.radioButtonGameWordsLanguageEnglish)
        Language.AM -> this.check(R.id.radioButtonGameWordsLanguageArmenian)
        Language.RU -> this.check(R.id.radioButtonGameWordsLanguageRussian)
    }

}

@BindingAdapter("bindWordTranslateLanguageCheckedButton")
fun RadioGroup.bindWordTranslateLanguageCheckedButton(settings: Settings) {
    when (settings.wordTranslateLanguage) {
        Language.EN -> this.check(R.id.radioButtonWordTranslateLanguageEnglish)
        Language.AM -> this.check(R.id.radioButtonWordTranslateLanguageArmenian)
        Language.RU -> this.check(R.id.radioButtonWordTranslateLanguageRussian)
    }

}