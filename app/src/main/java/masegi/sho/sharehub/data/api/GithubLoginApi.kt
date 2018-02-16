package masegi.sho.sharehub.data.api

import io.reactivex.Observable
import io.reactivex.Single
import masegi.sho.sharehub.data.model.AccessToken
import masegi.sho.sharehub.data.model.AuthModel
import masegi.sho.sharehub.data.model.Login
import retrofit2.http.*
import javax.inject.Singleton

/**
 * Created by masegi on 2018/02/11.
 */

@Singleton
interface GithubLoginApi {

    @GET("user") fun loginAccessToken(): Observable<Login>

    @POST("authorizations") fun login(@Body authModel: AuthModel): Observable<AccessToken>

    @FormUrlEncoded @POST("access_token")
    @Headers("Accept: application/json")
    fun getAccessToken(@Field("code") code: String,
                       @Field("client_id") clientId: String,
                       @Field("client_secret") clientSecret: String,
                       @Field("redirect_uri") redirectUrl: String
    ): Single<AccessToken>
}
