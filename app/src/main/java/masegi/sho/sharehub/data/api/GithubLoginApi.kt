package masegi.sho.sharehub.data.api

import masegi.sho.sharehub.data.model.AccessToken
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST
import javax.inject.Singleton

/**
 * Created by masegi on 2018/02/11.
 */

@Singleton
interface GithubLoginApi {

    @FormUrlEncoded @POST("access_token")
    @Headers("Accept: application/json")
    fun getAccessToken(@Field("code") code: String,
                       @Field("client_id") clientId: String,
                       @Field("client_secret") clientSecret: String,
                       @Field("redirect_uri") redirectUrl: String
    ): Call<AccessToken>
}
