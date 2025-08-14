package simple.library.weblog.base

interface DelegateListener {

    fun onOpen()

    fun onClose(reason: String?)

    fun onMessage(message: String?)

    fun onError(ex: Exception?)

    fun onPong()

}