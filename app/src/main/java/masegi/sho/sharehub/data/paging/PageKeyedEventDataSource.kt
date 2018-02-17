package masegi.sho.sharehub.data.paging

import android.annotation.SuppressLint
import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PageKeyedDataSource
import android.util.Log
import masegi.sho.sharehub.data.api.GithubApi
import masegi.sho.sharehub.data.model.Event
import masegi.sho.sharehub.data.model.NetworkState
import masegi.sho.sharehub.presentation.common.pref.Prefs
import java.io.IOException

/**
 * Created by masegi on 2018/02/17.
 */

class PageKeyedEventDataSource(private val api: GithubApi) : PageKeyedDataSource<Int, Event>() {


    // MARK: - Internal

    internal val networkState = MutableLiveData<NetworkState>()


    // MARK: - PageKeyedDataSource

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Event>) {

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Event>) {

        callApi(params.key) { events, next ->

            callback.onResult(events, next)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Event>) {

        callApi(1) { events, _ ->

            callback.onResult(events, null)
        }
    }


    // MARK: - Private

    @SuppressLint("LongLogTag")
    private fun callApi(page: Int, callback: (events: List<Event>, next: Int?) -> Unit) {

        networkState.postValue(NetworkState.RUNNING)
        var state = NetworkState.FAILED

        try {

            val response = api.getReceivedEvents(Prefs.login, page).execute()

            response.body()?.let {

                var next: Int? = null
                response.headers().get("Link")?.let {

                    val regex = Regex("rel=\"next\"")
                    if (regex.containsMatchIn(it)) {

                        next = page + 1
                    }
                }

                callback(it, next)
                state = NetworkState.SUCCESS
            }
        }
        catch (e: IOException) {

            Log.e("PageKeyedEventDataSource", e.message)
        }
        networkState.postValue(state)
    }
}