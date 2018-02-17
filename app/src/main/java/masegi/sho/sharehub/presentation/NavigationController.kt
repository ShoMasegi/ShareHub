package masegi.sho.sharehub.presentation

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.support.customtabs.CustomTabsIntent
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import androidx.net.toUri
import masegi.sho.sharehub.R
import masegi.sho.sharehub.presentation.login.LoginFragment
import masegi.sho.sharehub.presentation.login.SplashScreenFragment
import masegi.sho.sharehub.util.CustomTabsHelper
import javax.inject.Inject

/**
 * Created by masegi on 2018/02/10.
 */

class NavigationController @Inject constructor(private val activity: AppCompatActivity) {

    private val containerId: Int = R.id.content
    private val fragmentManager: FragmentManager = activity.supportFragmentManager

    fun navigateToLogin() {

        replaceFragment(LoginFragment.newInstance())
    }

    fun navigateToSplash() {

        replaceFragment(SplashScreenFragment.newInstance())
    }

    fun navigateToMainActivity() {

        MainActivity.start(activity)
    }

    private fun replaceFragment(fragment: Fragment) {

        fragmentManager
                .beginTransaction()
                .replace(containerId, fragment, null)
                .commitAllowingStateLoss()
    }

    fun navigationToExternalBrowser(url: String) {

        val customTabsPackageName = CustomTabsHelper.getPackageNameToUse(activity)
        if (tryLaunchingSpecificApp(url, customTabsPackageName)) {

            return
        }
        val customTabsIntent = CustomTabsIntent.Builder()
                .setShowTitle(true)
                .setToolbarColor(ContextCompat.getColor(activity, R.color.colorPrimary))
                .build()
                .apply {

                    val referrer = "android-app://${activity.packageName}".toUri()
                    intent.putExtra(Intent.EXTRA_REFERRER, referrer)
                }
        val webUri = url.toUri()
        if (tryUsingCustomTabs(customTabsPackageName, customTabsIntent, webUri)) {

            return
        }

        activity.startActivity(customTabsIntent.intent.setData(webUri))
    }

    private fun tryLaunchingSpecificApp(url: String, customTabsPackageName: String?): Boolean {

        val appUri = url.toUri().let {

            if (it.host.contains("facebook")) {

                (FACEBOOK_SCHEME + url).toUri()
            }
            else it
        }
        val appIntent = Intent(Intent.ACTION_VIEW, appUri)
        val intentResolveInfo = activity.packageManager.resolveActivity(
                appIntent,
                PackageManager.MATCH_DEFAULT_ONLY
        )
        intentResolveInfo?.activityInfo?.packageName?.let {

            if (customTabsPackageName != null && it != customTabsPackageName) {

                activity.startActivity(appIntent)
                return false
            }
        }
        return false
    }

    private fun tryUsingCustomTabs(customTabsPackageName: String?,
                                   customTabsIntent: CustomTabsIntent,
                                   webUri: Uri?): Boolean {

        customTabsPackageName?.let {

            customTabsIntent.intent.`package` = customTabsPackageName
            customTabsIntent.launchUrl(activity, webUri)
            return true
        }
        return false
    }

    interface FragmentReplacable {

        fun replaceFragment(fragment: Fragment)
    }

    companion object {

        private const val FACEBOOK_SCHEME = "fb://facewebmodal/f?href="
    }
}
