package masegi.sho.sharehub.util.ext

import android.view.View

/**
 * Created by masegi on 2018/02/16.
 */

fun View.setVisible(visible: Boolean) {

    if (visible) toVisible() else toGone()
}

fun View.toVisible() {

    visibility = View.VISIBLE
}

fun View.toInvisible() {

    visibility = View.INVISIBLE
}

fun View.toGone() {

    visibility = View.GONE
}