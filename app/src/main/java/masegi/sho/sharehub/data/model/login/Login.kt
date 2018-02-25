package masegi.sho.sharehub.data.model.login

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

/**
 * Created by masegi on 2018/02/14.
 */

@JsonSerializable
data class Login(
        val login: String,
        val id: Long,
        @Json(name = "avatar_url") val avatarUrl: String
)
