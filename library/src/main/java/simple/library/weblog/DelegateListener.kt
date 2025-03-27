package simple.library.weblog

interface DelegateListener {

    fun onOpen()

    fun onClose(code: Int, reason: String?, remote: Boolean)

    fun onMessage(message: String?)

    fun onError(ex: Exception?)

    fun onStart()

}