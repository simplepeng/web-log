package simple.demo.web_log

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import simple.library.weblog.ui.WebLogPage

class MainViewModel(val application: Application) : AndroidViewModel(application) {

    fun postAction(action: MainAction) {
        when (action) {
            MainAction.OpenComposeUI -> TODO()
            MainAction.OpenUI -> WebLogPage.open(application.applicationContext)
        }
    }
}