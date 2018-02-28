package masegi.sho.sharehub.presentation.event

import android.arch.lifecycle.ViewModel
import masegi.sho.sharehub.data.api.GithubApi
import masegi.sho.sharehub.data.repository.EventRepository
import javax.inject.Inject

/**
 * Created by masegi on 2018/02/14.
 */

class MainViewModel @Inject constructor(
        private val api: GithubApi
): ViewModel() {


    // MARK: - Property

    private val repository: EventRepository = EventRepository(api)
    internal val events = repository.events
    internal val networkState = repository.networkState
    internal val initialLoad = repository.initialLoad


    // MARK: - Internal

    fun refresh() {

        repository.refresh()
    }
}
