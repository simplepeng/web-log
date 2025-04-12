package simple.library.weblog.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import simple.library.weblog.base.WebLogHelper

internal class WebLogActivity : AppCompatActivity() {

    companion object {
        fun start(context: Context) {
            val starter = Intent(context, WebLogActivity::class.java)
            if (context !is Activity) {
                starter.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(starter)
        }
    }

    private val toolbar by lazy { findViewById<MaterialToolbar>(R.id.toolbar) }
    private val etIp by lazy { findViewById<TextInputEditText>(R.id.etIp) }
    private val etPort by lazy { findViewById<TextInputEditText>(R.id.etPort) }
    private val btnStart by lazy { findViewById<MaterialButton>(R.id.btnStart) }
    private val btnStop by lazy { findViewById<MaterialButton>(R.id.btnStop) }
    private val btnClear by lazy { findViewById<MaterialButton>(R.id.btnClear) }
    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.recyclerView) }

    private val viewModel: WebLogViewModel by lazy { ViewModelProvider(this)[WebLogViewModel::class.java] }
    private val messageAdapter: MessageAdapter by lazy { MessageAdapter(viewModel.messageList) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_web_log)

        initView()
        initListener()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initView() {
        etIp.setText(WebLogHelper.getIpAddress(this))
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = messageAdapter

        viewModel.addMessageLiveData.observe(this) { position ->
            messageAdapter.notifyItemInserted(position)
        }
        viewModel.clearMessageLiveData.observe(this) {
            messageAdapter.notifyDataSetChanged()
        }
    }

    private fun initListener() {
        toolbar.setNavigationOnClickListener {
            finish()
        }
        btnStart.setOnClickListener {
            start()
        }
        btnStop.setOnClickListener {
            stop()
        }
        btnClear.setOnClickListener {
            clear()
        }
    }

    private fun start() {
        val ip = etIp.text?.toString().orEmpty().trim()
        val port = etPort.text?.toString().orEmpty().trim()

        if (ip.isEmpty() || port.isEmpty()) {
            return
        }

        viewModel.start(port.toInt())
    }

    private fun stop() {
        viewModel.stop()
    }

    private fun clear() {
        viewModel.clear()
    }
}