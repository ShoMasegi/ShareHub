package masegi.sho.sharehub.data.model

import se.ansman.kotshi.JsonSerializable

/**
 * Created by masegi on 2018/02/17.
 */

@JsonSerializable
data class Commit(
        val sha: String,
        val author: Author,
        val message: String,
        val url: String
)