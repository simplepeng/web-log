package simple.library.weblog.ui

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import androidx.core.content.ContextCompat
import simple.library.weblog.WebLog

class KeepRunningService : Service() {

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, KeepRunningService::class.java)
            ContextCompat.startForegroundService(context, intent)
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        WebLog.startServer(context = this)
    }
}