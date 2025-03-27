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

    fun v(
        tag: String,
        message: String
    ) {
        server?.broadcast(
            MessageModel(
                level = MessageModel.LEVEL_VERBOSE,
                tag = tag,
                message = message
            ).toJson()
        )
    }

    fun d(
        tag: String,
        message: String
    ) {
        server?.broadcast(
            MessageModel(
                level = MessageModel.LEVEL_DEBUG,
                tag = tag,
                message = message
            ).toJson()
        )
    }

    fun i(
        tag: String,
        message: String
    ) {
        server?.broadcast(
            MessageModel(
                level = MessageModel.LEVEL_INFO,
                tag = tag,
                message = message
            ).toJson()
        )
    }

    fun w(
        tag: String,
        message: String
    ) {
        server?.broadcast(
            MessageModel(
                level = MessageModel.LEVEL_WARN,
                tag = tag,
                message = message
            ).toJson()
        )
    }

    fun e(
        tag: String,
        message: String
    ) {
        server?.broadcast(
            MessageModel(
                level = MessageModel.LEVEL_ERROR,
                tag = tag,
                message = message
            ).toJson()
        )
    }
}