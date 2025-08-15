package simple.library.weblog.server

import fi.iki.elonen.NanoHTTPD
import fi.iki.elonen.NanoWSD

internal abstract class AppWebSocket(
    handshakeRequest: NanoHTTPD.IHTTPSession
) : NanoWSD.WebSocket(handshakeRequest)