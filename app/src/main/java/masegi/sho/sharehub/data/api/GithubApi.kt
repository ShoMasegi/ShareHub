package masegi.sho.sharehub.data.api

import masegi.sho.sharehub.data.model.Event
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Singleton


/**
 * Created by masegi on 2018/02/11.
 */

@Singleton
interface GithubApi {

    @GET("users/{username}/received_events")
    fun getReceivedEvents(@Path("username") user: String,
                          @Query("page") page: Int): Call<List<Event>>
}