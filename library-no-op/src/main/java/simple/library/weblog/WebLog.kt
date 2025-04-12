package simple.library.weblog

import simple.library.weblog.base.DelegateListener
import simple.library.weblog.base.IWebLog

object WebLog : IWebLog {
    override fun start(port: Int, listener: DelegateListener?) {
    }

    override fun stop() {
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