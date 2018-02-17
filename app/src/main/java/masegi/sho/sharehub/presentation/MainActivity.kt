package masegi.sho.sharehub.presentation

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import masegi.sho.sharehub.R
import masegi.sho.sharehub.databinding.ActivityMainBinding
import masegi.sho.sharehub.presentation.common.BaseActivity
import masegi.sho.sharehub.presentation.common.DrawerMenu
import masegi.sho.sharehub.presentation.common.adapter.EventsAdapter
import masegi.sho.sharehub.util.ext.observe
import javax.inject.Inject

class MainActivity : BaseActivity() {


    // MARK: - Property

    @Inject lateinit var navigationController: NavigationController
    @Inject lateinit var drawerMenu: DrawerMenu
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    private val mainViewModel: MainViewModel by lazy {

        ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
    }

    private val binding by lazy {

        DataBindingUtil.setContentView<ActivityMainBinding>(
                this,
                R.layout.activity_main
        )
    }

    private val adapter = EventsAdapter()


    // MARK: - Activity

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.let {

            it.setDisplayHomeAsUpEnabled(false)
        }
        drawerMenu.setup(binding.drawerLayout, binding.drawer, binding.toolbar)

        setupLayout()
        mainViewModel.events.observe(this, { pagedList ->

            adapter.setList(pagedList)
        })
    }

    override fun onBackPressed() {

        if (drawerMenu.closeDrawerIfNeeded()) {

            super.onBackPressed()
        }
    }


    // MARK: - Private

    fun setupLayout() {

        binding.recycler.layoutManager = LinearLayoutManager(this)
        binding.recycler.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        binding.recycler.adapter = adapter
    }

    companion object {

        private fun createIntent(context: Context): Intent = Intent(context, MainActivity::class.java)

        fun start(context: Context) {

            createIntent(context).let {

                it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(it)
            }
        }
    }
}
