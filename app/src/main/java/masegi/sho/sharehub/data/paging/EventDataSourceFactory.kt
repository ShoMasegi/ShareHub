package masegi.sho.sharehub.data.paging

import android.arch.paging.DataSource
import masegi.sho.sharehub.data.api.GithubApi
import masegi.sho.sharehub.data.model.event.Event

/**
 * Created by masegi on 2018/02/17.
 */

class EventDataSourceFactory(private val api: GithubApi) : DataSource.Factory<Int, Event> {

    val source = PageKeyedEventDataSource(api)

    override fun create(): DataSource<Int, Event> {

        return source
    }
}