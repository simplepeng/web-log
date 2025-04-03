package simple.library.weblog

import org.java_websocket.WebSocket
import org.java_websocket.handshake.ClientHandshake
import java.lang.Exception

object WebLog {

    private var server: AndroidWebSocketServer? = null

    fun start(
        port: Int,
        listener: DelegateListener? = null
    ) {
        server = object : AndroidWebSocketServer(port) {
            override fun onOpen(conn: WebSocket?, handshake: ClientHandshake?) {
                listener?.onOpen()
            }

            override fun onClose(conn: WebSocket?, code: Int, reason: String?, remote: Boolean) {
                listener?.onClose(code, reason, remote)
            }

            override fun onMessage(conn: WebSocket?, message: String?) {
                listener?.onMessage(message)
            }

            override fun onError(conn: WebSocket?, ex: Exception?) {
                listener?.onError(ex)
            }

            override fun onStart() {
                listener?.onStart()
            }
        }
        server?.start()
    }

    fun stop() {
        server?.stop()
    }

    fun broadcast(
        message: String
    ) {
        v("WebLog", message)
    }

    fun v(
        tag: String,
        message: String
    ) {
        server?.broadcast(
            Message(
                level = Message.LEVEL_VERBOSE,
                tag = tag,
                message = message
            ).toJson()
        )
    }

    fun d(
        tag: String,
        message: String
    ) {
        server?.broadcast(
            Message(
                level = Message.LEVEL_DEBUG,
                tag = tag,
                message = message
            ).toJson()
        )
    }

    fun i(
        tag: String,
        message: String
    ) {
        server?.broadcast(
            Message(
                level = Message.LEVEL_INFO,
                tag = tag,
                message = message
            ).toJson()
        )
    }

    fun w(
        tag: String,
        message: String
    ) {
        server?.broadcast(
            Message(
                level = Message.LEVEL_WARN,
                tag = tag,
                message = message
            ).toJson()
        )
    }

    fun e(
        tag: String,
        message: String
    ) {
        server?.broadcast(
            Message(
                level = Message.LEVEL_ERROR,
                tag = tag,
                message = message
            ).toJson()
        )
    }
}