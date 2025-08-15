package simple.library.weblog.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.widget.Toast
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.content.pm.ShortcutManagerCompat
import androidx.core.net.toUri
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
            Toast.makeText(context, "已复制到粘贴板", Toast.LENGTH_SHORT).show()
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }

    fun createShortcuts(context: Context) {
        val shortcutInfo = ShortcutInfoCompat.Builder(context, "web_log_page")
            .setShortLabel("Open WebLog Page")
//            .setActivity(ComponentName(context, WebLogActivity::class.java))
            .setIntent(Intent(context, WebLogActivity::class.java).apply {
                setAction(Intent.ACTION_VIEW)
            })
            .build()

        ShortcutManagerCompat.addDynamicShortcuts(context, listOf(shortcutInfo))
    }
}