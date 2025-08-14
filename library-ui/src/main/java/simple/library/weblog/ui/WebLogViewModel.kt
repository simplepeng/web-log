package simple.library.weblog.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import simple.library.weblog.WebLog
import simple.library.weblog.base.DelegateListener

internal class WebLogViewModel : ViewModel() {

    val messageList = mutableListOf<String>()

    val addMessageLiveData = MutableLiveData<Int>()
    val clearMessageLiveData = MutableLiveData<Unit>()

    fun startWebServer(
        hostName: String,
        port: Int
    ) {
        WebLog.startWebServer(hostName = hostName, port = port)
    }

    fun stopWebServer() {
        WebLog.stopWebServer()
    }

    fun startSocketServer(
        hostName: String,
        port: Int
    ) {
        if (WebLog.socketServerIsStarted) {
            addMessage("服务正在运行中...")
            return
        }

        WebLog.addSocketListener(object : DelegateListener {
            override fun onOpen() {
                addMessage("客户端连接成功")
                WebLog.v("Server", "服务端连接成功")
            }

            override fun onClose(code: Int, reason: String?, remote: Boolean) {
                addMessage("SocketServer关闭 $reason -- $remote")
            }

            override fun onMessage(message: String?) {
                addMessage("收到消息 -- $message")
            }

            override fun onError(ex: Exception?) {
                addMessage("发生异常 -- ${ex?.message}")
            }

            override fun onStart() {
                addMessage("SocketServer启动成功")
            }
        })
        WebLog.startSocketServer(hostName = hostName, port = port)
    }

    fun stopSocketServer() {
        WebLog.stopSocketServer()
        addMessage("SocketServe已关闭")
    }

    fun clear() {
        messageList.clear()
        clearMessageLiveData.postValue(Unit)
    }

    fun addMessage(message: String) {
        messageList.add(message)
        addMessageLiveData.postValue(messageList.size - 1)
    }
}