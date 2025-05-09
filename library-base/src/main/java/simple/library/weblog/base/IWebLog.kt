package simple.library.weblog.base

interface IWebLog {

    fun start(
        port: Int,
        listener: DelegateListener? = null
    )

    fun stop()

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