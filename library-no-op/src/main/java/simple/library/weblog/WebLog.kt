package simple.library.weblog

import simple.library.weblog.base.DelegateListener
import simple.library.weblog.base.IWebLog

object WebLog : IWebLog {

    override fun addSocketListener(listener: DelegateListener) {
    }

    override fun removeSocketListener(listener: DelegateListener) {
    }

    override fun startSocketServer(port: Int) {
    }

    override fun startServer() {
    }

    override fun stopSocketServer() {
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