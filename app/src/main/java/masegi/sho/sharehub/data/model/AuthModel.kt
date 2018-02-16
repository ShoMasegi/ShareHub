package masegi.sho.sharehub.data.model

import com.squareup.moshi.Json
import masegi.sho.sharehub.BuildConfig
import masegi.sho.sharehub.util.GithubLoginUtils
import org.parceler.Parcel

/**
 * Created by masegi on 2018/02/14.
 */

@Parcel
data class AuthModel(
        val clientId: String = GithubLoginUtils.clientId,
        val clientSecret: String = GithubLoginUtils.clientSecret,
        var scope: List<String> = listOf("user", "repo", "gist", "notifications", "read:org"),
        val note: String = BuildConfig.APPLICATION_ID,
        val noteUrl: String = GithubLoginUtils.redirectUrl,
        @Json(name = "X-GitHub-OTP") var otp: String?
) {

    private constructor() : this(otp = null)

}
