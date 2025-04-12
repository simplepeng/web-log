package simple.library.weblog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import simple.library.weblog.base.DelegateListener

//@Preview
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = viewModel(),
) {

    val context = LocalContext.current
    var ip by remember { mutableStateOf("") }
    var port by remember { mutableStateOf("8080") }
    val tag = remember { "Test" }

    val messageList by viewModel.messageList.collectAsState()
    val listState = rememberLazyListState()

    LaunchedEffect(messageList) {
        if (messageList.isNotEmpty()) {
            listState.scrollToItem(messageList.size - 1)
        }
    }

    Scaffold(
        modifier = modifier
    ) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.spacedBy(5.dp),
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(10.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.wrapContentWidth()
            ) {
                OutlinedTextField(
                    value = ip,
                    onValueChange = { },
                    readOnly = true,
                    modifier = Modifier.wrapContentWidth(),
                    label = {
                        Text("ip")
                    },
                )
                OutlinedTextField(
                    value = port,
                    onValueChange = { port = it },
                    label = {
                        Text("port")
                    },
                )
            }
            //
            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(onClick = {
                    WebLog.start(port.toInt(), object : DelegateListener {
                        override fun onOpen() {
                            viewModel.addMessage("客户端连接成功")
                            WebLog.v("WebLog", "服务端连接成功")
                        }

                        override fun onClose(code: Int, reason: String?, remote: Boolean) {
                            viewModel.addMessage("WebSocket服务关闭 $reason -- $remote")
                        }

                        override fun onMessage(message: String?) {
                            viewModel.addMessage("收到消息")
                        }

                        override fun onError(ex: Exception?) {
                            viewModel.addMessage("发生异常 -- ${ex?.message}")
                        }

                        override fun onStart() {
                            viewModel.addMessage("WebSocket服务启动成功")
                        }
                    })
                }) {
                    Text(
                        text = "start"
                    )
                }
                Button(onClick = {
                    WebLog.stop()
                }) {
                    Text(
                        text = "stop"
                    )
                }
                Button(onClick = {
                    viewModel.clear()
                }) {
                    Text(
                        text = "clear"
                    )
                }
            }
            //
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Button(onClick = {
                    val message = "发送了一条debug日志"
                    WebLog.d(tag, message)
                    viewModel.addMessage(message)
//                    messageList.add(message)
                }) {
                    Text(
                        text = "debug"
                    )
                }
                Button(onClick = {
                    val message = "发送了一条info日志"
                    WebLog.i(tag, message)
                    viewModel.addMessage(message)
                }) {
                    Text(
                        text = "info"
                    )
                }
                Button(onClick = {
                    val message = "发送了一条warn日志"
                    WebLog.w(tag, message)
                    viewModel.addMessage(message)
                }) {
                    Text(
                        text = "warn"
                    )
                }
                Button(onClick = {
                    val message = "发送了一条error日志"
                    WebLog.e(tag, message)
                    viewModel.addMessage(message)
                }) {
                    Text(
                        text = "error"
                    )
                }
            }
            //
            LazyColumn(
                state = listState,
                verticalArrangement = Arrangement.spacedBy(10.dp),
                reverseLayout = false,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(10.dp)
            ) {
                items(messageList) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = it,
                            modifier = Modifier.padding(10.dp)
                        )
                    }
                }
            }
        }
    }
}