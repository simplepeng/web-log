package simple.library.weblog.server

import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import simple.library.weblog.R
import simple.library.weblog.WebLog

class KeepRunningService : Service() {

    companion object {
        private const val NOTIFICATION_ID = 1
        private const val NOTIFICATION_CHANNEL_ID = "WebLogService"

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

        NotificationChannelCompat.Builder(
            NOTIFICATION_CHANNEL_ID,
            NotificationManager.IMPORTANCE_DEFAULT
        ).setName("WebLog服务")
            .build()
            .let {
                NotificationManagerCompat.from(this).createNotificationChannel(it)
            }

        startForeground(
            NOTIFICATION_ID,
            NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.drawable.baseline_arrow_back_24)
                .setContentTitle("WebLog服务正在运行")
                .setContentText("HHH")
                .build()
        )
    }
}