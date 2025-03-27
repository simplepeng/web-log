package simple.library.weblog

import org.json.JSONObject

data class MessageModel(
    val time: Long = System.currentTimeMillis(),
    val level: Int,
    //
    val tag: String,
    val message: String,
) {
    companion object {
        const val LEVEL_VERBOSE = 0
        const val LEVEL_DEBUG = 1
        const val LEVEL_INFO = 2
        const val LEVEL_WARN = 3
        const val LEVEL_ERROR = 4
    }

    fun toJson(): String {
        return JSONObject().apply {
            put("time", time)
            put("level", level)
            put("tag", tag)
            put("message", message)
        }.toString()
    }
}
