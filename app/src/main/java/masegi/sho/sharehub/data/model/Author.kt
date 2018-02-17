package masegi.sho.sharehub.data.model

import se.ansman.kotshi.JsonSerializable

/**
 * Created by masegi on 2018/02/17.
 */

@JsonSerializable
data class Author(
        val name: String,
        val email: String?
)
