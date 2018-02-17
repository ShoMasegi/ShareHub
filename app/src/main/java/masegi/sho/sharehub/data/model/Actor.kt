package masegi.sho.sharehub.data.model

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

/**
 * Created by masegi on 2018/02/17.
 */

@JsonSerializable
data class Actor(
        val id: Long,
        val login: String,
        @Json(name = "display_login") val displayLogin: String,
        val url: String,
        @Json(name = "avatar_url") val avatarUrl: String
)