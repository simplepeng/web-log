package simple.library.weblog

import fi.iki.elonen.NanoHTTPD
import fi.iki.elonen.NanoWSD
import java.io.IOException

internal class AppWebSocket(handshakeRequest: NanoHTTPD.IHTTPSession) : NanoWSD.WebSocket(handshakeRequest) {

    override fun onOpen() {
        LogHelper.debug("AppWebSocket -- onOpen")
    }

    override fun onClose(code: NanoWSD.WebSocketFrame.CloseCode?, reason: String?, initiatedByRemote: Boolean) {
        LogHelper.debug("AppWebSocket -- onClose")
    }

    override fun onMessage(message: NanoWSD.WebSocketFrame?) {
        LogHelper.debug("AppWebSocket -- onMessage")
    }

    override fun onPong(pong: NanoWSD.WebSocketFrame?) {
        LogHelper.debug("AppWebSocket -- onPong")
    }

    override fun onException(exception: IOException?) {
        LogHelper.debug("AppWebSocket -- onException")
    }
}