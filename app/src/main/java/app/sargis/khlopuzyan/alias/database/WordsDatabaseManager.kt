package app.sargis.khlopuzyan.alias.database

import android.content.Context
import androidx.lifecycle.LiveData
import app.sargis.khlopuzyan.alias.model.Word
import javax.inject.Inject

/**
 * Created by Sargis Khlopuzyan, on 2/28/2020.
 *
 * @author Sargis Khlopuzyan (sargis.khlopuzyan@fcc.am)
 */

class WordsDatabaseManager @Inject constructor(val context: Context) {

    fun saveWordInDatabase(word: Word): Long {
        return AliasDb.getInstance(context).getWordsDAO().insertWord(word)
    }

    fun deleteWordFromDatabase(word: Word): Int {
        return AliasDb.getInstance(context).getWordsDAO().deleteWord(word.uuid)
    }

    fun getWordFromDatabase(nameEn: String): Word? {
        return AliasDb.getInstance(context).getWordsDAO().getWordByName(nameEn)
    }

    fun getAllMatchedWordsFromDatabase(nameEn: String): List<Word> {
        return AliasDb.getInstance(context).getWordsDAO().getAllMatchedWords(nameEn)
    }

    fun getAllWordsFromDatabase(): List<Word> {
        return AliasDb.getInstance(context).getWordsDAO().getAllWords()
    }

    fun getAllWordsLiveDataFromDatabase(): LiveData<List<Word>?> {
        return AliasDb.getInstance(context).getWordsDAO().getAllWordsLiveData()
    }

}