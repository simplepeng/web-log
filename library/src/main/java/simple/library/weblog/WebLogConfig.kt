package simple.library.weblog

import android.annotation.SuppressLint
import android.content.Context

object WebLogConfig {

    private val sharedPreferences by lazy {
        WebLogInitProvider.applicationContext?.getSharedPreferences("config_web_log", Context.MODE_PRIVATE)
    }

    private const val DEFAULT_PORT = 8081

    var port: Int
        get() = sharedPreferences?.getInt("port", DEFAULT_PORT) ?: DEFAULT_PORT
        @SuppressLint("UseKtx")
        set(value) = sharedPreferences?.edit()?.putInt("port", value)?.apply() ?: Unit
    
}