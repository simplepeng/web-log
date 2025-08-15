package simple.library.weblog.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.net.toUri

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
}