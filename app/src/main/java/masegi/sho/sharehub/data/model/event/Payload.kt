package masegi.sho.sharehub.data.model.event

import com.squareup.moshi.Json
import masegi.sho.sharehub.data.model.*
import se.ansman.kotshi.JsonSerializable

/**
 * Created by masegi on 2018/02/17.
 */

@JsonSerializable
data class Payload(
        val comment: Comment?,
        val ref_type: String?,
        val ref: String?,
        val description: String?,
        val deployment: Deployment?,
        val repository: Repo?,
        val target: User?,
        val forkee: Forkee?,
        val action: String?,
        val number: Int?,
        val pull_request: PullRequest?,
        val review: Review?,
        val size: Int = 0,
        val gist: Gist?,
        val issue: Issue?,
        @Json(name = "push_id") val pushId: Long?,
        val commits: List<Commit>?
)