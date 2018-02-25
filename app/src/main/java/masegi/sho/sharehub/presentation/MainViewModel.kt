package masegi.sho.sharehub.presentation

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import masegi.sho.sharehub.data.api.GithubApi
import masegi.sho.sharehub.data.model.event.Event
import masegi.sho.sharehub.data.model.NetworkState
import masegi.sho.sharehub.data.paging.EventDataSourceFactory
import javax.inject.Inject

/**
 * Created by masegi on 2018/02/14.
 */

class MainViewModel @Inject constructor(
        private val api: GithubApi
): ViewModel() {


    // MARK: - Internal

    internal val events: LiveData<PagedList<Event>>

    internal val networkState: LiveData<NetworkState>

    init {

        val factory = EventDataSourceFactory(api)
        val config = PagedList.Config.Builder()
                .setInitialLoadSizeHint(50)
                .setPageSize(50)
                .build()

        events = LivePagedListBuilder(factory, config).build()
        networkState = factory.source.networkState
    }
}
