package app.sargis.khlopuzyan.alias.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import app.sargis.khlopuzyan.alias.database.converter.TeamNameConverter
import app.sargis.khlopuzyan.alias.database.converter.WordConverter
import app.sargis.khlopuzyan.alias.database.dao.TeamNamesDAO
import app.sargis.khlopuzyan.alias.database.dao.WordsDAO
import app.sargis.khlopuzyan.alias.model.TeamName
import app.sargis.khlopuzyan.alias.model.Word

/**
 * Created by Sargis Khlopuzyan, on 2/28/2020.
 *
 * @author Sargis Khlopuzyan (sargis.khlopuzyan@fcc.am)
 */
@TypeConverters(
    TeamNameConverter::class,
    WordConverter::class
)
@Database(entities = [TeamName::class, Word::class], version = 1, exportSchema = false)
abstract class AliasDb : RoomDatabase() {

    abstract fun getTeamNamesDAO(): TeamNamesDAO

    abstract fun getWordsDAO(): WordsDAO

    companion object {

        @Volatile
        private var INSTANCE: AliasDb? = null

        fun getInstance(context: Context): AliasDb {

            if (INSTANCE == null) {

                synchronized(AliasDb::class) {

                    if (INSTANCE == null) {

                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            AliasDb::class.java, "alias.db"
                        )
                            .allowMainThreadQueries()
                            .build()
                    }
                }
            }
            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}