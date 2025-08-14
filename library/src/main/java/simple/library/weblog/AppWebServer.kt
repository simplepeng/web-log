package simple.library.weblog

import android.content.Context
import fi.iki.elonen.NanoWSD

internal class AppWebServer(
    private val context: Context,
    hostName: String,
    port: Int
) : NanoWSD(hostName, port) {

    private var appWebSocket: AppWebSocket? = null

    fun broadcast(message: String) {
        Thread {
            appWebSocket?.send(message)
        }.start()
    }

    override fun openWebSocket(handshake: IHTTPSession): WebSocket {
        if (appWebSocket == null) {
            appWebSocket = AppWebSocket(handshake)
        }
        return appWebSocket!!
    }

    override fun serveHttp(session: IHTTPSession): Response? {
        return serveResponse(session)
    }

    private fun serveResponse(session: IHTTPSession): Response? {
        var uri = session.uri

        if (uri == "/") {
            uri = "/index.html"
        }

        try {
            val inputStream = context.assets.open("docs$uri")
            val mimeType = getMimeTypeForFile2(uri)
            return newChunkedResponse(Response.Status.OK, mimeType, inputStream)
        } catch (e: Throwable) {
            // 文件不存在时返回 404
            return newFixedLengthResponse(Response.Status.NOT_FOUND, "text/plain", "404 Not Found: $uri");
        }
    }

    private fun getMimeTypeForFile2(uri: String): String {
        if (uri.endsWith(".html")) return "text/html"
        if (uri.endsWith(".js")) return "application/javascript"
        if (uri.endsWith(".css")) return "text/css"
        if (uri.endsWith(".png")) return "image/png"
        if (uri.endsWith(".jpg") || uri.endsWith(".jpeg")) return "image/jpeg"
        if (uri.endsWith(".svg")) return "image/svg+xml"
        if (uri.endsWith(".wasm")) return "application/wasm"

        return "application/octet-stream"
    }
}