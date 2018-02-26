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
        val type: EventType?,
        val actor: User?,
        val repo: Repo?,
        val payload: Payload,
        @Json(name = "public") val publicEvent: Boolean?,
        @Json(name = "created_at") val createdAt: LocalDateTime
)
{

    enum class EventType {

        COMMIT_COMMENT,
        CREATE,
        DELETE,
        DEPLOYMENT,
        DOWNLOAD,
        FOLLOW,
        FORK,
        FORK_APPLY,
        GIST,
        GOLLUM,
        INSTALLATION,
        INSTALLATION_REPOSITORIES,
        ISSUE_COMMENT,
        ISSUES,
        LABEL,
        MARKETPLACE_PURCHASE,
        MEMBER,
        MEMBERSHIP,
        MILESTONE,
        ORGANIZATION,
        ORG_BLOCK,
        PAGE_BUILD,
        PROJECT_CARD,
        PROJECT_COLUMN,
        PROJECT,
        PUBLIC_EVENT,
        PULL_REQUEST,
        PULL_REQUEST_REVIEW,
        PULL_REQUEST_REVIEW_COMMENT,
        PUSH,
        RELEASE,
        REPOSITORY,
        STATUS,
        TEAM,
        TEAM_ADD,
        WATCH
    }
}
