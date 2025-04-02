package simple.demo.web_log

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {

    private val _messageList = MutableStateFlow<List<String>>(emptyList())
    val messageList = _messageList.asStateFlow()

    fun addMessage(message: String) {
        _messageList.value = _messageList.value + message
    }

    fun clear(){
        _messageList.value = emptyList()
    }
}