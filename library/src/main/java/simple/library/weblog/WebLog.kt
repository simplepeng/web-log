package simple.library.weblog

object WebLog {

    private var server: AndroidWebSocketServer? = null

    fun start(port: Int) {
        server = AndroidWebSocketServer(port)
        server?.start()
    }

    fun stop() {
        server?.stop()
    }

    fun debug(message: String) {
        server?.broadcast(message)
    }
}