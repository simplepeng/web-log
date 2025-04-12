package simple.library.weblog

import org.java_websocket.WebSocket
import org.java_websocket.handshake.ClientHandshake
import simple.library.weblog.base.DelegateListener
import simple.library.weblog.base.IWebLog
import java.lang.Exception

object WebLog : IWebLog {

    private var server: AndroidWebSocketServer? = null

    override fun start(
        port: Int,
        listener: DelegateListener?
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

    override fun stop() {
        server?.stop()
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
        server?.broadcast(
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
        server?.broadcast(
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
        server?.broadcast(
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
        server?.broadcast(
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
        server?.broadcast(
            Message(
                level = Message.LEVEL_ERROR,
                tag = tag,
                message = message
            ).toJson()
        )
    }
}