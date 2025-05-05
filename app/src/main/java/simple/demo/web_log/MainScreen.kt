package simple.demo.web_log

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalLayoutApi::class)
@Preview
@Composable
fun MainScreen(
    viewModel: MainViewModel = viewModel()
) {
    FlowRow(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(start = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Button(onClick = {
            viewModel.postAction(MainAction.OpenUI)
        }) {
            Text(
                text = "open  ui"
            )
        }

        Button(onClick = {
            viewModel.postAction(MainAction.OpenComposeUI)
        }) {
            Text(
                text = "open compose ui"
            )
        }
    }
}