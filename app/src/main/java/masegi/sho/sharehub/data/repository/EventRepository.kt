package masegi.sho.sharehub.data.repository

import android.arch.lifecycle.LiveData
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import masegi.sho.sharehub.data.api.GithubApi
import masegi.sho.sharehub.data.model.NetworkState
import masegi.sho.sharehub.data.model.event.Event
import masegi.sho.sharehub.data.paging.EventDataSourceFactory

/**
 * Created by masegi on 2018/02/28.
 */

class EventRepository(
        private val githubApi: GithubApi
)
{


    // MARK: - Property

    internal val events: LiveData<PagedList<Event>>
    internal val networkState: LiveData<NetworkState>

    private val factory = EventDataSourceFactory(githubApi)


    // MARK: - Initializer

    init {

        val config = PagedList.Config.Builder()
                .setInitialLoadSizeHint(50)
                .setPageSize(50)
                .build()

        events = LivePagedListBuilder(factory, config).build()
        networkState = factory.source.networkState
    }


    // MARK: - Internal

    fun refresh() {

        factory.source.invalidate()
    }
}