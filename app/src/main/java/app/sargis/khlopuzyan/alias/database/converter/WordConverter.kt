package app.sargis.khlopuzyan.alias.database.converter

import androidx.room.TypeConverter
import app.sargis.khlopuzyan.alias.model.Word
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by Sargis Khlopuzyan, on 2/28/2020.
 *
 * @author Sargis Khlopuzyan (sargis.khlopuzyan@fcc.am)
 */
class WordConverter {

    private val gson = Gson()

    @TypeConverter
    fun stringToWord(data: String?): Word {

        if (data == null) {
            return Word()
        }

        val type = object : TypeToken<Word>() {}.type

        return gson.fromJson<Word>(data, type)
    }

    @TypeConverter
    fun wordToString(word: Word): String {
        return gson.toJson(word)
    }
}