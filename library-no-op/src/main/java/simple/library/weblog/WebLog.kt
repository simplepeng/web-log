package simple.library.weblog

import android.content.Context
import simple.library.weblog.base.DelegateListener
import simple.library.weblog.base.IWebLog

object WebLog : IWebLog {

    override fun addSocketListener(listener: DelegateListener) {
    }

    override fun removeSocketListener(listener: DelegateListener) {
    }

    override fun startServer(context: Context) {
    }

    override fun startServer(context: Context, hostName: String, port: Int) {
    }

    override fun stopServer() {
    }

    override fun broadcast(tag: String, message: String) {
    }

    override fun v(tag: String, message: String) {
    }

    override fun d(tag: String, message: String) {
    }

    override fun i(tag: String, message: String) {
    }

    override fun w(tag: String, message: String) {
    }

    override fun e(tag: String, message: String) {
    }
}