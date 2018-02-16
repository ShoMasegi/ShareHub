package masegi.sho.sharehub.data.model

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

/**
 * Created by masegi on 2018/02/01.
 */

@JsonSerializable
data class AccessToken(
        @Json(name = "access_token") val accessToken: String?,
        @Json(name = "token_type") val tokenType: String?,
        val token: String?,
        @Json(name = "hashed_token") val hashedToken: String?

)
