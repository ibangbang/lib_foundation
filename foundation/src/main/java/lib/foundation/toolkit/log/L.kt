package lib.foundation.toolkit.log

import android.util.Log

object L {

    var TAG_DEFAULT = "LOG"
    var enable = false

    fun enableLog(e: Boolean = false) {
        enable = e
    }

    fun d(tag: String? = TAG_DEFAULT, msg: String?) {
        if (enable)
            Log.d(tag, msg)
    }

    fun e(tag: String? = TAG_DEFAULT, msg: String?) {
        if (enable)
            Log.e(tag, msg)
    }

}