package masegi.sho.sharehub.data.model

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable
import java.util.*

/**
 * Created by masegi on 2018/02/17.
 */

@JsonSerializable
data class Event(
        val id: Int,
        val type: String,
        val actor: Actor,
        val repo: Repo,
        @Json(name = "payload") val payload: Payload,
        val public: Boolean
//        @Json(name = "created_at") val createdAt: Date
)
