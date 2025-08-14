package simple.library.weblog

import android.annotation.SuppressLint
import android.app.Application
import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.net.Uri


class WebLogInitProvider : ContentProvider() {

    companion object {
        var applicationContext: Context? = null
    }

    /**
     * 用反射获取Application
     */
    @SuppressLint("PrivateApi")
    fun getApplicationByReflect(): Application {
        try {
            val activityThread = Class.forName("android.app.ActivityThread")
            val thread = activityThread.getMethod("currentActivityThread").invoke(null)
            val app = activityThread.getMethod("getApplication").invoke(thread)
            if (app == null) {
                throw NullPointerException("you should init first")
            }
            return app as Application
        } catch (e: Exception) {
            e.printStackTrace()
        }
        throw NullPointerException("you should init first")
    }

    override fun onCreate(): Boolean {
        applicationContext = context?.applicationContext
        if (applicationContext == null) {
            applicationContext = getApplicationByReflect()
        }

        applicationContext?.let {
            WebLog.startServer(it)
        }

        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String?>?,
        selection: String?,
        selectionArgs: Array<out String?>?,
        sortOrder: String?
    ): Cursor? {
        return null
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String?>?): Int {
        return 0
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String?>?
    ): Int {
        return 0
    }
}