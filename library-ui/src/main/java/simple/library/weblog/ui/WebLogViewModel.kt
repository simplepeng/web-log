package simple.library.weblog.ui

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import simple.library.weblog.WebLog
import simple.library.weblog.base.DelegateListener

internal class WebLogViewModel : ViewModel() {

    val messageList = mutableListOf<String>()

    val addMessageLiveData = MutableLiveData<Int>()
    val clearMessageLiveData = MutableLiveData<Unit>()

    fun start(port: Int) {
        WebLog.start(port, object : DelegateListener {
            override fun onOpen() {
                addMessage("客户端连接成功")
            }

            override fun onClose(code: Int, reason: String?, remote: Boolean) {
                addMessage("WebSocket服务关闭 $reason -- $remote")
            }

            override fun onMessage(message: String?) {
                addMessage("收到消息")
            }

            override fun onError(ex: Exception?) {
                addMessage("发生异常 -- ${ex?.message}")
            }

            override fun onStart() {
                addMessage("WebSocket服务启动成功")
            }
        })
    }

    fun stop() {
        WebLog.stop()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clear() {
        messageList.clear()
        clearMessageLiveData.postValue(Unit)
    }

    fun addMessage(message: String) {
        messageList.add(message)
        addMessageLiveData.postValue(messageList.size - 1)
    }
}