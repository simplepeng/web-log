package simple.library.weblog

import android.annotation.SuppressLint
import android.content.Context

object WebLogConfig {

    private val sharedPreferences by lazy {
        WebLogInitProvider.applicationContext?.getSharedPreferences("config_web_log", Context.MODE_PRIVATE)
    }

    private const val DEFAULT_WEB_SERVER_PORT = 8080
    private const val DEFAULT_SOCKET_SERVER_PORT = 8081

    private const val KEY_SOCKET_SERVER_PORT = "socket_server_port"
    private const val KEY_WEB_SERVER_PORT = "web_server_port"

    var webServerPort: Int
        get() = sharedPreferences?.getInt(KEY_WEB_SERVER_PORT, DEFAULT_WEB_SERVER_PORT) ?: DEFAULT_WEB_SERVER_PORT
        @SuppressLint("UseKtx")
        set(value) = sharedPreferences?.edit()?.putInt(KEY_WEB_SERVER_PORT, value)?.apply() ?: Unit

    var socketServerPort: Int
        get() = sharedPreferences?.getInt(KEY_SOCKET_SERVER_PORT, DEFAULT_SOCKET_SERVER_PORT) ?: DEFAULT_SOCKET_SERVER_PORT
        @SuppressLint("UseKtx")
        set(value) = sharedPreferences?.edit()?.putInt(KEY_SOCKET_SERVER_PORT, value)?.apply() ?: Unit

}