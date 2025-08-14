package simple.library.weblog

import android.annotation.SuppressLint
import android.content.Context
import org.java_websocket.WebSocket
import org.java_websocket.handshake.ClientHandshake
import simple.library.weblog.base.DelegateListener
import simple.library.weblog.base.IWebLog
import simple.library.weblog.base.WebLogHelper
import java.lang.Exception

object WebLog : IWebLog {

    @SuppressLint("StaticFieldLeak")
    private var webServer: AppWebServer? = null

    private var socketServer: AppWebSocketServer? = null

    val socketServerIsStarted: Boolean
        get() = socketServer != null

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

    override fun startServer() {
        WebLogInitProvider.applicationContext?.let {
            try {
                val hostName = getHostName(it, WebLogConfig.hostName)
                startWebServer(
                    hostName = hostName,
                    port = WebLogConfig.webServerPort
                )
                startSocketServer(
                    hostName = hostName,
                    port = WebLogConfig.socketServerPort
                )
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }
    }

    override fun stopServer() {
        stopWebServer()
        stopSocketServer()
    }

    override fun startWebServer(
        hostName: String,
        port: Int
    ) {
        WebLogInitProvider.applicationContext?.let {
            if (webServer == null) {
                webServer = AppWebServer(
                    context = it,
                    hostName = getHostName(it, hostName),
                    port = port
                )
            }
            webServer?.start()
        }
    }

    override fun stopWebServer() {
        webServer?.stop()
        webServer = null
    }

    override fun startSocketServer(
        hostName: String,
        port: Int,
    ) {
        if (socketServerIsStarted) {
            return
        }

        WebLogConfig.socketServerPort = port

        socketServer = object : AppWebSocketServer(hostName, port) {
            override fun onOpen(conn: WebSocket?, handshake: ClientHandshake?) {
                socketListeners.forEach { it.onOpen() }
            }

            override fun onClose(conn: WebSocket?, code: Int, reason: String?, remote: Boolean) {
                socketListeners.forEach { it.onClose(code, reason, remote) }
            }

            override fun onMessage(conn: WebSocket?, message: String?) {
                socketListeners.forEach { it.onMessage(message) }
            }

            override fun onError(conn: WebSocket?, ex: Exception?) {
                socketListeners.forEach { it.onError(ex) }
            }

            override fun onStart() {
                socketListeners.forEach { it.onStart() }
            }
        }

        socketServer?.start()
    }

    override fun stopSocketServer() {
        socketServer?.stop()
        socketServer = null
        socketListeners.clear()
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
        socketServer?.broadcast(
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
        socketServer?.broadcast(
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
        socketServer?.broadcast(
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
        socketServer?.broadcast(
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
        socketServer?.broadcast(
            Message(
                level = Message.LEVEL_ERROR,
                tag = tag,
                message = message
            ).toJson()
        )
    }
}