package simple.library.weblog

import org.java_websocket.server.WebSocketServer
import java.net.InetSocketAddress

internal abstract class AppWebSocketServer(
    port: Int
) : WebSocketServer(InetSocketAddress(port))
