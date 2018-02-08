package masegi.sho.sharehub.data.model

import com.squareup.moshi.Json

/**
 * Created by masegi on 2018/02/01.
 */

data class AccessToken(
        @Json(name = "access_token") val accessToken: String,
        @Json(name = "token_type") val tokenType: String
)
