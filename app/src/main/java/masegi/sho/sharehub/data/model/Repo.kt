package masegi.sho.sharehub.data.model

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

/**
 * Created by masegi on 2018/02/17.
 */

@JsonSerializable
data class Repo(
        val id: Long,
        val name: String,
        val fullName: String?,
        val owner: Actor?,
        @Json(name = "private") val privateRepo: Boolean?,
        val description: String?,
        val url: String
)