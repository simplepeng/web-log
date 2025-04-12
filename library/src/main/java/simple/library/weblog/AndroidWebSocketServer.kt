package simple.library.weblog

import org.java_websocket.server.WebSocketServer
import java.net.InetSocketAddress

internal abstract class AndroidWebSocketServer(
    port: Int
) : WebSocketServer(InetSocketAddress(port))
