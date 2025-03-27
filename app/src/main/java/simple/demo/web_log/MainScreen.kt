package simple.demo.web_log

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import simple.library.weblog.WebLog
import simple.library.weblog.WebLogHelper

@Preview
@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    var ip by remember { mutableStateOf(WebLogHelper.getIpAddress(context)) }
    var port by remember { mutableStateOf("8080") }

    Scaffold(
        modifier = modifier
    ) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
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
                    onValueChange = { ip = it },
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

            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(onClick = {
                    WebLog.start(port.toInt())
                }) {
                    Text(
                        text = "start"
                    )
                }
                Button(onClick = {
                    WebLog.debug("123")
                }) {
                    Text(
                        text = "stop"
                    )
                }
            }
        }
    }
}