package simple.library.weblog.server

import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.IconCompat
import simple.library.weblog.R
import simple.library.weblog.WebLog
import simple.library.weblog.configs.WebLogConfig
import simple.library.weblog.utils.Helper

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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannelCompat.Builder(NOTIFICATION_CHANNEL_ID, NotificationManager.IMPORTANCE_HIGH)
                .setName("WebLog")
                .build()
                .let {
                    NotificationManagerCompat.from(this).createNotificationChannel(it)
                }
        }

        startForeground(
            NOTIFICATION_ID,
            NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("WebLog服务正在运行")
                .setContentText(WebLogConfig.webServerUrl)
//                .setContentInfo(WebLogConfig.webServerUrl)
                .setAutoCancel(false)
                .setOngoing(true)
                .build()
        )
    }
}