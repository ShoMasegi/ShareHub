package masegi.sho.sharehub.presentation.event


import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment

import masegi.sho.sharehub.data.model.NetworkState
import masegi.sho.sharehub.databinding.FragmentMainBinding
import masegi.sho.sharehub.presentation.NavigationController
import masegi.sho.sharehub.presentation.common.adapter.EventsAdapter
import masegi.sho.sharehub.util.ext.observe
import javax.inject.Inject

class MainFragment : DaggerFragment() {


    // MARK: - Property

    @Inject lateinit var navigationController: NavigationController
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: FragmentMainBinding

    private val mainViewModel: MainViewModel by lazy {

        ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
    }

    private lateinit var adapter: EventsAdapter


    // MARK: - Fragment

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = FragmentMainBinding.inflate(inflater, container!!, false)
        adapter = EventsAdapter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        setupLayout()
        setupSwipeToRefresh()
        mainViewModel.events.observe(this) {

            adapter.setList(it)
        }
    }


    // MARK: - Private

    private fun setupLayout() {

        binding.recycler.layoutManager = LinearLayoutManager(context)
        binding.recycler.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        binding.recycler.adapter = adapter
    }

    private fun setupSwipeToRefresh() {

        mainViewModel.initialLoad.observe(this) {

            binding.swipeRefresh.isRefreshing = it == NetworkState.RUNNING
        }
        binding.swipeRefresh.setOnRefreshListener {

            mainViewModel.refresh()
        }
    }


    companion object {

        fun newInstance(): MainFragment = MainFragment()
    }
}// Required empty public constructor