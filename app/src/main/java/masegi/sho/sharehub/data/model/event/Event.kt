package masegi.sho.sharehub.data.model.event

import com.squareup.moshi.Json
import masegi.sho.sharehub.data.model.User
import masegi.sho.sharehub.data.model.Repo
import org.threeten.bp.LocalDateTime
import se.ansman.kotshi.JsonSerializable

/**
 * Created by masegi on 2018/02/17.
 */

@JsonSerializable
data class Event(
        val id: Long,
        val type: EventType,
        val user: User,
        val repo: Repo,
        val payload: Payload?,
        @Json(name = "public") val publicEvent: Boolean?,
        @Json(name = "created_at") val createdAt: LocalDateTime
)
