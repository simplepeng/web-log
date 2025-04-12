package simple.demo.web_log

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MainScreen() {
    FlowRow {
        Button(onClick = {

        }) {
            Text(
                text = "open compose ui"
            )
        }

        Button(onClick = {

        }) {
            Text(
                text = "open  ui"
            )
        }
    }
}