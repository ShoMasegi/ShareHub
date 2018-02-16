package masegi.sho.sharehub.data.api

import io.reactivex.Observable
import io.reactivex.Single
import masegi.sho.sharehub.data.model.AccessToken
import masegi.sho.sharehub.data.model.AuthModel
import masegi.sho.sharehub.data.model.Login
import retrofit2.http.*

/**
 * Created by masegi on 2018/02/14.
 */

interface LoginService {

    @GET("user")
    fun loginAccessToken(): Single<Login>

    @POST("authorizations")
    fun login(@Body authModel: AuthModel)

    @FormUrlEncoded
    @POST("access_token")
    @Headers("Accept: application/json")
    fun getAccessToken(@Field("code") code: String,
                       @Field("client_id") clientId: String,
                       @Field("client_secret") clientSecret: String,
                       @Field("redirect_uri") redirectUrl: String
    ): Single<AccessToken>

}
