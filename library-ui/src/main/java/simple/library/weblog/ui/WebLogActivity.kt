package simple.library.weblog.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity

class WebLogActivity : ComponentActivity() {

    companion object{
        fun start(context: Context) {
            val starter = Intent(context, WebLogActivity::class.java)
            context.startActivity(starter)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}