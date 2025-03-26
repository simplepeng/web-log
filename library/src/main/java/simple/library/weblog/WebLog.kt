package simple.library.weblog

object WebLog {

    fun start() {
        val server = AndroidWebSocketServer()
        server.start()
    }

    fun stop() {

    }
}