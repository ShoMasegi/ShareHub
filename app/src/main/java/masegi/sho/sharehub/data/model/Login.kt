package masegi.sho.sharehub.data.model

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

/**
 * Created by masegi on 2018/02/14.
 */

@JsonSerializable
data class Login(
        @Json(name = "login") val login: String?,
        @Json(name = "id") val id: Long?,
        @Json(name = "avatar_url") val avatarUrl: String?,
        @Json(name = "email") val email: String?
)
