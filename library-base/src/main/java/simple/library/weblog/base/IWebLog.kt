package simple.library.weblog.base

import android.content.Context

interface IWebLog {

    fun addSocketListener(listener: DelegateListener)

    fun removeSocketListener(listener: DelegateListener)

    fun startServer(context: Context)

    fun startServer(
        context: Context,
        hostName: String,
        port: Int
    )

    fun stopServer()

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