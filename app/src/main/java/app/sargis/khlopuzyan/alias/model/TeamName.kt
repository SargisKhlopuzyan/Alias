package app.sargis.khlopuzyan.alias.model

import android.os.Parcelable
import androidx.room.Entity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

/**
 * Created by Sargis Khlopuzyan, on 2/28/2020.
 *
 * @author Sargis Khlopuzyan (sargis.khlopuzyan@fcc.am)
 */
@Entity(tableName = "teamNames")
@Parcelize
@JsonClass(generateAdapter = true)
data class TeamName(

    @Json(name = "uuid")
    var uuid: Int? = 0,

    @Json(name = "team_name")
    var teamName: String? = null

) : Parcelable