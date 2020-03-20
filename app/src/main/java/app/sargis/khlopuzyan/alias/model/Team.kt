package app.sargis.khlopuzyan.alias.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

/**
 * Created by Sargis Khlopuzyan, on 2/28/2020.
 *
 * @author Sargis Khlopuzyan (sargis.khlopuzyan@fcc.am)
 */
@Entity(tableName = "teams")
@Parcelize
@JsonClass(generateAdapter = true)
data class Team(

    @PrimaryKey(autoGenerate = true)
    var dbRowId: Long = 0.toLong(),

    @Json(name = "uuid")
    var uuid: String? = null,

    @Json(name = "name")
    var name: String? = null,

    @Ignore
    var totalScore: Int = 0,

    @Ignore
    var roundScores: MutableMap<Int, Int> = HashMap(),

    @Ignore
    var words: MutableList<Word> = mutableListOf()

) : Parcelable