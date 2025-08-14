package simple.library.weblog.base

interface IWebLog {

    fun addListener(listener: DelegateListener)

    fun removeListener(listener: DelegateListener)

    fun startServer()

    fun stopServer()

    fun startWebServer(
        hostName: String,
        port: Int
    )

    fun stopWebServer()

    fun startSocketServer(
        hostName: String,
        port: Int
    )

    fun stopSocketServer()

    fun broadcast(
        tag: String,
        message: String
    )

    fun v(
        tag: String,
        message: String
    )

    fun d(
        tag: String,
        message: String
    )


    fun i(
        tag: String,
        message: String
    )

    fun w(
        tag: String,
        message: String
    )

    fun e(
        tag: String,
        message: String
    )

}