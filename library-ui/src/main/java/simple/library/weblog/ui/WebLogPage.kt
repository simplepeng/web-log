package simple.library.weblog.ui

import android.content.Context
import simple.library.weblog.base.IWebLogPage

object WebLogPage : IWebLogPage{

    override fun open(context: Context) {
        WebLogActivity.start(context)
    }
}