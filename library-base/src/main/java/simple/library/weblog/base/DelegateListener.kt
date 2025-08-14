package simple.library.weblog.base

interface DelegateListener {

    fun onOpen()

    fun onClose()

    fun onMessage(message: String?)

    fun onError(ex: Exception?)

    fun onStart()

}