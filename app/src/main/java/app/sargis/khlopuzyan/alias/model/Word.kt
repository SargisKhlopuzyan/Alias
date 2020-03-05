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
@Entity(tableName = "words")
@Parcelize
@JsonClass(generateAdapter = true)
data class Word(

    @PrimaryKey(autoGenerate = true)
    var dbRowId: Long = 0.toLong(),

    @Json(name = "uuid")
    var uuid: String? = null,

    @Json(name = "word_en")
    var wordEn: String? = null,

    @Json(name = "word_am")
    var wordAm: String? = null,

    @Json(name = "word_ru")
    var wordRu: String? = null,

    @Ignore
    var isGuessed: Boolean = false,

    @Ignore
    var isWordTranslateVisible: Boolean = false

) : Parcelable