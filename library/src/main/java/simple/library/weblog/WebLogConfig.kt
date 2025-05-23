package simple.library.weblog

import android.annotation.SuppressLint
import android.content.Context

object WebLogConfig {

    private val sharedPreferences by lazy {
        WebLogInitProvider.applicationContext?.getSharedPreferences("config_web_log", Context.MODE_PRIVATE)
    }

    var port: Int
        get() = sharedPreferences?.getInt("port", 8080) ?: 8080
        @SuppressLint("UseKtx")
        set(value) = sharedPreferences?.edit()?.putInt("port", value)?.apply() ?: Unit
    
}