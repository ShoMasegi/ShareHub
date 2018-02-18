package masegi.sho.sharehub.presentation.common.binding

import android.databinding.BindingAdapter
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import masegi.sho.sharehub.util.CustomGlideApp

/**
 * Created by masegi on 2018/02/18.
 */

@BindingAdapter(
        value = [
            "image_url",
            "placeHolder",
            "android:layout_width",
            "android:layout_height"
        ]
)
fun ImageView.loadImage(url: String?, placeHolder: Drawable, width: Float, height: Float) {

    if (url != null) {

        CustomGlideApp
                .with(this)
                .load(url)
                .placeholder(placeHolder)
                .override(width.toInt(), height.toInt())
                .dontAnimate()
                .transform(CircleCrop())
                .into(this)
    }
}

