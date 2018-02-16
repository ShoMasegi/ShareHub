package masegi.sho.sharehub.util.ext

import retrofit2.HttpException

/**
 * Created by masegi on 2018/02/16.
 */

val Throwable.code: Int
    get() =
        if (this is HttpException) (this as HttpException).code() else -1
