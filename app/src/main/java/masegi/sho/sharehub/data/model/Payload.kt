package masegi.sho.sharehub.data.model

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

/**
 * Created by masegi on 2018/02/17.
 */

@JsonSerializable
data class Payload(
        @Json(name = "push_id") val pushId: Long,
        val ref: String,
        val commits: List<Commit>
)