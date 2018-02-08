package masegi.sho.sharehub.data.api.client

import masegi.sho.sharehub.data.model.AccessToken
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

/**
 * Created by masegi on 2018/02/02.
 */

interface LoginClient {

    @Headers("Accept: application/json")
    @POST("access_token")
    @FormUrlEncoded
    fun getAccessToken(
            @Field("client_id") clientId: String,
            @Field("client_secret") clientSecret: String,
            @Field("code") code: String
    ): Call<AccessToken>
}