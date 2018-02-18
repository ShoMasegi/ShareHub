package masegi.sho.sharehub.util.ext

import org.threeten.bp.Duration
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter

/**
 * Created by masegi on 2018/02/18.
 */

val LocalDateTime.durationFromToday: String
    get() {

        val duration = Duration.between(this, LocalDateTime.now())

        return when {

            duration.toMinutes() <= 60 -> duration.toMinutes().toString() + " minutes ago"
            duration.toHours() <= 24 -> duration.toHours().toString() + " hours ago"
            duration.toDays() <= 7 -> duration.toDays().toString() + " days ago"
            else -> {

                val formatter = DateTimeFormatter.ofPattern("dd MMM")
                "on " + this.format(formatter)
            }
        }
    }
