package simple.library.weblog

import org.java_websocket.WebSocket
import org.java_websocket.handshake.ClientHandshake
import org.java_websocket.server.WebSocketServer
import java.lang.Exception

class AndroidWebSocketServer : WebSocketServer() {

    override fun onOpen(conn: WebSocket?, handshake: ClientHandshake?) {
    }

    override fun onClose(conn: WebSocket?, code: Int, reason: String?, remote: Boolean) {
    }

    override fun onMessage(conn: WebSocket?, message: String?) {
    }

    override fun onError(conn: WebSocket?, ex: Exception?) {
    }

    override fun onStart() {
    }
}