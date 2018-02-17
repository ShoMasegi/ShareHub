package masegi.sho.sharehub.data.model

import com.squareup.moshi.Json
import se.ansman.kotshi.GetterName
import se.ansman.kotshi.JsonSerializable
import java.util.*

/**
 * Created by masegi on 2018/02/17.
 */

@JsonSerializable
data class Event(
        val id: Long,
        val type: String,
        val actor: Actor,
        val repo: Repo,
        val payload: Payload?,
        val publicEvent: Boolean?
//        @Json(name = "created_at") val createdAt: Date
)
