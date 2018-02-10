package masegi.sho.sharehub.presentation.common

import android.support.annotation.IdRes
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import masegi.sho.sharehub.R
import masegi.sho.sharehub.presentation.NavigationController
import javax.inject.Inject
import kotlin.reflect.KClass

/**
 * Created by masegi on 2018/02/10.
 */

class DrawerMenu @Inject constructor(
        private val activity: AppCompatActivity,
        private val navigationController: NavigationController
) {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var currentNavigationItem: DrawerNavigationitem

    fun setup(
            drawerLayout: DrawerLayout,
            navigationView: NavigationView,
            toolbar: Toolbar? = null,
            actionBarDrawerSync: Boolean = false
    )
    {

        this.drawerLayout = drawerLayout
        if (actionBarDrawerSync) {

            object : ActionBarDrawerToggle(
                    activity,
                    drawerLayout,
                    toolbar,
                    R.string.navigation_drawer_open,
                    R.string.navigation_drawer_close
            ) { }
                    .also {

                        drawerLayout.addDrawerListener(it)
                    }
                    .apply {

                        isDrawerIndicatorEnabled = true
                        isDrawerSlideAnimationEnabled = false
                        syncState()
                    }
        }
        navigationView.setNavigationItemSelectedListener { item ->

            DrawerNavigationitem
                    .values()
                    .first { it.menuId == item.itemId }
                    .apply {

                        if (this != currentNavigationItem) {

                            navigate(navigationController)
                        }
                    }
            drawerLayout.closeDrawers()
            false
        }
        currentNavigationItem = DrawerNavigationitem
                .values()
                .firstOrNull { activity::class == it.activityClass }
                ?.also {

                    navigationView.setCheckedItem(it.menuId)
                }
                ?: DrawerNavigationitem.OTHER
    }

    fun closeDrawerIfNeeded(): Boolean {

        return if (drawerLayout.isDrawerOpen(GravityCompat.START)) {

            drawerLayout.closeDrawers()
            false
        }
        else {

            true
        }
    }

    enum class DrawerNavigationitem(
            @IdRes val menuId: Int,
            val activityClass: KClass<*>,
            val navigate: NavigationController.() -> Unit
    )
    {

        OTHER(0, Unit::class, {

        })
    }
}

