package simple.library.weblog.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.widget.Toast
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.content.pm.ShortcutManagerCompat
import androidx.core.graphics.drawable.IconCompat
import androidx.core.net.toUri
import simple.library.weblog.R
import simple.library.weblog.ui.WebLogActivity

internal object Helper {

    fun open(
        context: Context,
        url: String,
    ) {
        try {
            val uri = url.toUri()
            val intent = Intent(Intent.ACTION_VIEW, uri)
            context.startActivity(intent)
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }

    fun copy(
        context: Context,
        url: String,
    ) {
        try {
            val manager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            manager.setPrimaryClip(ClipData.newPlainText(null, url))
//            Toast.makeText(context, context.getString(R.string.is_copied), Toast.LENGTH_SHORT).show()
            Toast.makeText(context, url, Toast.LENGTH_SHORT).show()
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }

    fun createShortcuts(context: Context) {
        val shortcutInfo = ShortcutInfoCompat.Builder(context, "web_log_page")
            .setIcon(IconCompat.createWithResource(context, R.mipmap.icon_web_log))
            .setShortLabel(context.getString(R.string.open_web_log_page))
            .setIntent(
                Intent(context, WebLogActivity::class.java).apply {
                    setAction(Intent.ACTION_VIEW)
                })
            .build()

        ShortcutManagerCompat.addDynamicShortcuts(context, listOf(shortcutInfo))
    }

    fun readAutoStart(context: Context): Boolean {
        return try {
            context.packageManager.getApplicationInfo(
                context.packageName,
                PackageManager.GET_META_DATA
            ).metaData.getBoolean("WebLogAutoStart", true)
        } catch (_: Throwable) {
            true
        }
    }

    fun openNotificationSetting(context: Context) {
        val intent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Android 8.0+ 有单独的通知设置页
            Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS).apply {
                putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName)
            }
        } else {
            // 老版本跳到应用详情页
            Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                data = "package:${context.packageName}".toUri()
            }
        }
        context.startActivity(intent)
    }
}