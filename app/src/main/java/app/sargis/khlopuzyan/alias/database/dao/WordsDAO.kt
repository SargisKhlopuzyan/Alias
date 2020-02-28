package app.sargis.khlopuzyan.alias.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import app.sargis.khlopuzyan.alias.model.Word

/**
 * Created by Sargis Khlopuzyan, on 2/28/2020.
 *
 * @author Sargis Khlopuzyan (sargis.khlopuzyan@fcc.am)
 */
@Dao
interface WordsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWord(item: Word): Long

    @Update
    fun updateWord(vararg words: Word): Int

    @Query("DELETE FROM words WHERE uuid = :id")
    fun deleteWord(id: String?): Int

    @Query("SELECT * FROM words WHERE wordEn = :name")
    fun getWordByName(name: String?): Word?

    @Query("SELECT * FROM words WHERE wordEn LIKE :name")
    fun getAllMatchedWords(name: String?): List<Word>

    @Query("SELECT * FROM words")
    fun getAllWords(): List<Word>

    @Query("SELECT * FROM words")
    fun getAllWordsLiveData(): LiveData<List<Word>?>

}