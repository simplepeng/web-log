package simple.library.weblog

import android.util.Log

internal object LogHelper {

    private const val TAG = "WebLog"

    fun debug(message: String) {
        Log.d(TAG, message)
    }
}