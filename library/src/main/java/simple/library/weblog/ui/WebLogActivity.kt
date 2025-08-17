package simple.library.weblog.ui

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import simple.library.weblog.utils.Helper
import simple.library.weblog.R
import simple.library.weblog.WebLog
import simple.library.weblog.configs.WebLogConfig
import simple.library.weblog.base.DelegateListener
import simple.library.weblog.base.WebLogHelper

internal class WebLogActivity : AppCompatActivity() {

    companion object {
        private const val REQ_NOTIFICATION_PERMISSION_CODE = 120

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

    private val etWebServerPort by lazy { findViewById<TextInputEditText>(R.id.etWebServerPort) }
    private val btnWebServerStart by lazy { findViewById<MaterialButton>(R.id.btnWebServerStart) }
    private val btnWebServerStop by lazy { findViewById<MaterialButton>(R.id.btnWebServerStop) }

//    private val etSocketServerPort by lazy { findViewById<TextInputEditText>(R.id.etSocketServerPort) }
//    private val btnSocketServerStart by lazy { findViewById<MaterialButton>(R.id.btnSocketServerStart) }
//    private val btnSocketServerStop by lazy { findViewById<MaterialButton>(R.id.btnSocketServerStop) }

    private val btnOpen by lazy { findViewById<View>(R.id.btnOpen) }
    private val btnCopy by lazy { findViewById<View>(R.id.btnCopy) }

    private val btnDebug by lazy { findViewById<MaterialButton>(R.id.btnDebug) }
    private val btnInfo by lazy { findViewById<MaterialButton>(R.id.btnInfo) }
    private val btnWarn by lazy { findViewById<MaterialButton>(R.id.btnWarn) }
    private val btnError by lazy { findViewById<MaterialButton>(R.id.btnError) }
    private val btnClear by lazy { findViewById<MaterialButton>(R.id.btnClear) }
    private val btnPermission by lazy { findViewById<MaterialButton>(R.id.btnPermission) }

    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.recyclerView) }
    private val viewModel: WebLogViewModel by lazy { ViewModelProvider(this)[WebLogViewModel::class.java] }
    private val messageAdapter: MessageAdapter by lazy { MessageAdapter(viewModel.messageList) }

    private val tag = "Test"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_web_log)

        initView()
        initListener()
        initData()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initView() {
        etIp.setText(WebLogHelper.getIpAddress(this))
        etWebServerPort.setText(WebLogConfig.webServerPort.toString())
//        etSocketServerPort.setText(WebLogConfig.socketServerPort.toString())

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = messageAdapter

        viewModel.addMessageLiveData.observe(this) { position ->
            messageAdapter.notifyItemInserted(position)
            recyclerView.smoothScrollToPosition(position)
        }
        viewModel.clearMessageLiveData.observe(this) {
            messageAdapter.notifyDataSetChanged()
        }
    }

    private fun initListener() {
        WebLog.addSocketListener(object : DelegateListener {
            override fun onOpen() {
                viewModel.addMessage("客户端连接成功")
            }

            override fun onClose(reason: String?) {
                viewModel.addMessage("SocketServer关闭 -- $reason")
            }

            override fun onMessage(message: String?) {
                viewModel.addMessage("收到消息 -- $message")
            }

            override fun onError(ex: Exception?) {
                viewModel.addMessage("发生异常 -- ${ex?.message}")
            }

            override fun onPong() {
                viewModel.addMessage("SocketServer启动成功")
            }
        })

        toolbar.setNavigationOnClickListener {
            finish()
        }
        btnWebServerStart.setOnClickListener {
            startWebServer()
        }
        btnWebServerStop.setOnClickListener {
            stopWebServer()
        }
        btnOpen.setOnClickListener {
            Helper.open(this, getUrl())
        }
        btnCopy.setOnClickListener {
            Helper.copy(this, getUrl())
        }
//        btnSocketServerStart.setOnClickListener {
//            startSocketServer()
//        }
//        btnSocketServerStop.setOnClickListener {
//            stopSocketServer()
//        }
        btnClear.setOnClickListener {
            clear()
        }
        btnPermission.setOnClickListener {
            Helper.openNotificationSetting(this)
        }
        btnDebug.setOnClickListener {
            val message = "发送了一条debug日志"
            WebLog.d(tag, message)
            viewModel.addMessage(message)
        }
        btnInfo.setOnClickListener {
            val message = "发送了一条info日志"
            WebLog.i(tag, message)
            viewModel.addMessage(message)
        }
        btnWarn.setOnClickListener {
            val message = "发送了一条warn日志"
            WebLog.w(tag, message)
            viewModel.addMessage(message)
        }
        btnError.setOnClickListener {
            val message = "发送了一条error日志"
            WebLog.e(tag, message)
            viewModel.addMessage(message)
        }
    }

    private fun initData() {
        checkStarted()
    }

    private fun checkStarted() {
        recyclerView.post {
            if (WebLog.isStarted) {
                viewModel.addMessage("Server is Running")
            } else {
                viewModel.addMessage("Server is Done")
            }
        }
    }

    private fun startWebServer() {
        val ip = etIp.text?.toString().orEmpty().trim()
        val port = etWebServerPort.text?.toString().orEmpty().trim()

        if (ip.isEmpty() || port.isEmpty()) {
            return
        }

        WebLog.startServer(this, hostName = ip, port = port.toInt())
        checkStarted()
    }

    private fun stopWebServer() {
        WebLog.stopServer()
        checkStarted()
    }

//    private fun startSocketServer() {
//        val ip = etIp.text?.toString().orEmpty().trim()
//        val port = etSocketServerPort.text?.toString().orEmpty().trim()
//
//        if (ip.isEmpty() || port.isEmpty()) {
//            return
//        }
//
//        viewModel.startSocketServer(hostName = ip, port = port.toInt())
//    }
//
//    private fun stopSocketServer() {
//        viewModel.stopSocketServer()
//    }

    private fun clear() {
        viewModel.clear()
    }

    private fun getUrl(): String {
        val ip = etIp.text?.toString().orEmpty().trim()
        val port = etWebServerPort.text?.toString().orEmpty().trim()
        return "http://$ip:$port"
    }

    private fun requestNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) { // Android 13+
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_DENIED
            ) {
//                ActivityCompat.shouldShowRequestPermissionRationale()
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    REQ_NOTIFICATION_PERMISSION_CODE
                )
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String?>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}