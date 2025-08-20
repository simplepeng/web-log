package simple.library.weblog

import android.annotation.SuppressLint
import android.content.Context
import fi.iki.elonen.NanoHTTPD
import simple.library.weblog.base.DelegateListener
import simple.library.weblog.base.IWebLog
import simple.library.weblog.base.WebLogHelper
import simple.library.weblog.configs.WebLogConfig
import simple.library.weblog.data.Message
import simple.library.weblog.server.AppWebServer

object WebLog : IWebLog {

    @SuppressLint("StaticFieldLeak")
    private var webServer: AppWebServer? = null

    val isStarted: Boolean
        get() = webServer?.isAlive == true

    private fun getHostName(
        context: Context,
        hostName: String
    ) = hostName.ifEmpty {
        WebLogHelper.getIpAddress(context)
    }

    private val socketListeners = mutableListOf<DelegateListener>()

    override fun addSocketListener(listener: DelegateListener) {
        socketListeners.add(listener)
    }

    override fun removeSocketListener(listener: DelegateListener) {
        socketListeners.remove(listener)
    }

    override fun startServer(context: Context) {
        try {
            startServer(
                context,
                hostName = WebLogHelper.getIpAddress(context),
                port = WebLogConfig.webServerPort
            )
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }

    override fun startServer(
        context: Context,
        hostName: String,
        port: Int
    ) {
        try {
            if (webServer == null) {
                webServer = AppWebServer(context, socketListeners, hostName, port)
            }
            if (webServer?.wasStarted() == false) {
                webServer?.start(0, false)
//                WebLogConfig.hostName = hostName
//                WebLogConfig.webServerPort = port
            }
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }

    override fun stopServer() {
        webServer?.stop()
        webServer = null
    }

    override fun broadcast(
        tag: String,
        message: String
    ) {
        v(tag, message)
    }

    override fun v(
        tag: String,
        message: String
    ) {
        webServer?.broadcast(
            Message(
                level = Message.LEVEL_VERBOSE,
                tag = tag,
                message = message
            ).toJson()
        )
    }

    override fun d(
        tag: String,
        message: String
    ) {
        webServer?.broadcast(
            Message(
                level = Message.LEVEL_DEBUG,
                tag = tag,
                message = message
            ).toJson()
        )
    }

    override fun i(
        tag: String,
        message: String
    ) {
        webServer?.broadcast(
            Message(
                level = Message.LEVEL_INFO,
                tag = tag,
                message = message
            ).toJson()
        )
    }

    override fun w(
        tag: String,
        message: String
    ) {
        webServer?.broadcast(
            Message(
                level = Message.LEVEL_WARN,
                tag = tag,
                message = message
            ).toJson()
        )
    }

    override fun e(
        tag: String,
        message: String
    ) {
        webServer?.broadcast(
            Message(
                level = Message.LEVEL_ERROR,
                tag = tag,
                message = message
            ).toJson()
        )
    }
}