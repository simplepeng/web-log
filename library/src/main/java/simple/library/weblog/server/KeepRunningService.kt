package simple.library.weblog.server

import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Icon
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
import simple.library.weblog.ui.WebLogActivity
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

        start()
    }

    private fun start() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannelCompat.Builder(NOTIFICATION_CHANNEL_ID, NotificationManager.IMPORTANCE_NONE)
                .setName("WebLog")
                .build()
                .let {
                    NotificationManagerCompat.from(this).createNotificationChannel(it)
                }
        }

        startForeground(
            NOTIFICATION_ID,
            NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.mipmap.icon_web_log)
//                .setLargeIcon(Icon.createWithResource(this,R.mipmap.icon_web_log))
                .setContentTitle("WebLog服务正在运行")
                .setContentText(WebLogConfig.webServerUrl)
                .setContentIntent(
                    PendingIntent.getActivity(
                        this, 111,
                        Intent(this, WebLogActivity::class.java),
                        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                    )
                )
                .setAutoCancel(false)
                .setOngoing(true)
                .build()
        )

        WebLog.startServer(context = this)
    }
}