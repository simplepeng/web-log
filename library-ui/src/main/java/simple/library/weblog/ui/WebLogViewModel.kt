package simple.library.weblog.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

internal class WebLogViewModel : ViewModel() {

    val messageList = mutableListOf<String>()

    val addMessageLiveData = MutableLiveData<Int>()
    val clearMessageLiveData = MutableLiveData<Unit>()

    fun clear() {
        messageList.clear()
        clearMessageLiveData.postValue(Unit)
    }

    fun addMessage(message: String) {
        messageList.add(message)
        addMessageLiveData.postValue(messageList.size - 1)
    }
}