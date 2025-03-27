package simple.library.weblog

import org.java_websocket.WebSocket
import org.java_websocket.handshake.ClientHandshake
import org.java_websocket.server.WebSocketServer
import java.lang.Exception
import java.net.InetSocketAddress

class AndroidWebSocketServer(
    port: Int
) : WebSocketServer(InetSocketAddress(port)) {

    override fun onOpen(conn: WebSocket?, handshake: ClientHandshake?) {
        LogHelper.debug("onOpen")
    }

    override fun onClose(conn: WebSocket?, code: Int, reason: String?, remote: Boolean) {
        LogHelper.debug("onClose")
    }

    override fun onMessage(conn: WebSocket?, message: String?) {
        LogHelper.debug("onMessage -- ${message.orEmpty()}")
        conn?.send("你好呀")
    }

    override fun onError(conn: WebSocket?, ex: Exception?) {
        LogHelper.debug("onError")
        ex?.printStackTrace()
    }

    override fun onStart() {
        LogHelper.debug("onStart")
    }
}